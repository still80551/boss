package com.still.bos.service.bos.take_delivery.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.AreaRepository;
import com.still.bos.dao.base.FixedAreaRepository;
import com.still.bos.dao.take_delivery.OrderRepository;
import com.still.bos.dao.take_delivery.WorkBillRepository;
import com.still.bos.domain.base.Area;
import com.still.bos.domain.base.Courier;
import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;
import com.still.bos.domain.take_delivery.Order;
import com.still.bos.domain.take_delivery.WorkBill;
import com.still.bos.service.bos.take_delivery.OrderService;

/**  
 * ClassName:OrderServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月24日 下午7:25:07 <br/>       
 */


@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private AreaRepository areaRepository;
    
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    
    @Autowired 
    private WorkBillRepository workBillRepository;

    @Override
    public void saveOrder(Order order) {
        
        //传过来的order里面的area是瞬时态,要转为持久态
        Area sendArea = order.getSendArea();
        if(sendArea != null){
           Area sendAreaDB = areaRepository.findByProvinceAndCityAndDistrict(sendArea.getProvince()
                    ,sendArea.getCity(),sendArea.getDistrict());
            
           order.setSendArea(sendAreaDB);
        }
        
        Area recArea = order.getRecArea();
        if(recArea != null){
           Area recAreaDB  = areaRepository.findByProvinceAndCityAndDistrict(recArea.getProvince()
                    ,recArea.getCity(),recArea.getDistrict());
           order.setRecArea(recAreaDB);
        }
        
        //保存订单
        order.setOrderNum(UUID.randomUUID().toString().replaceAll("-", ""));//设置订单号
        order.setOrderTime(new Date()); // 设置下单时间
        orderRepository.save(order);
        
       //自动分单
        String sendAddress = order.getSendAddress();
        if(StringUtils.isNotEmpty(sendAddress)){
         // ---根据发件地址完全匹配
            // 让crm系统根据发件地址查询定区ID
            String fixedAreaId = WebClient.create("http://localhost:8380/crm/webService/customerService/findFixedAreaIdByAddress")
            .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .query("address", sendAddress)
            .get(String.class);
            
            //fixedAreaId存在
            if(StringUtils.isNotEmpty(fixedAreaId)){
                
                //根据定区ID找到定区
                FixedArea fixedArea = fixedAreaRepository.findOne(Long.parseLong(fixedAreaId));
                if(fixedArea != null){
                    //拿到属于这个定区的快递员
                    Set<Courier> couriers = fixedArea.getCouriers();
                    if(!couriers.isEmpty()){
                        //通过迭代器获取第一个快递员
                       Iterator<Courier> iterator = couriers.iterator();
                       Courier courier = iterator.next();
                       
                       //指派快递员
                       order.setCourier(courier);
                       //生成工单
                       WorkBill workBill = new WorkBill();
                       workBill.setAttachbilltimes(0);
                       workBill.setBuildtime(new Date());
                       workBill.setOrder(order);
                       workBill.setCourier(courier);
                       workBill.setRemark(order.getRemark());
                       workBill.setSmsNumber("111");
                       workBill.setPickstate("新单");
                       workBill.setType("新");
                       
                       //保存工单
                       workBillRepository.save(workBill);
                       
                       //设置为自动下单
                       order.setOrderType("自动下单");
                       return;
                    }
                }
            }else{
                //没有获取到定区ID , 根据区域--分区--定区
                Area sendArea2 = order.getSendArea();
                if(sendArea2 != null){
                    Set<SubArea> subareas = sendArea2.getSubareas();
                    
                    for (SubArea subArea : subareas) {
                        String keyWords = subArea.getKeyWords();
                        String assistKeyWords = subArea.getAssistKeyWords();
                        
                        if(StringUtils.isNotEmpty(assistKeyWords)&&StringUtils.isNotEmpty(keyWords)){
                            //如果寄件人地址中包含了关键字
                            if(sendAddress.contains(assistKeyWords)||sendAddress.contains(keyWords)){
                                //通过分区找到定区
                                FixedArea fixedArea = subArea.getFixedArea(); 
                                if(fixedArea != null){
                                    Set<Courier> couriers = fixedArea.getCouriers();
                                    Iterator<Courier> iterator = couriers.iterator();
                                    Courier courier = iterator.next();
                                    
                                    //指派快递员
                                    order.setCourier(courier);
                                  //生成工单
                                    WorkBill workBill = new WorkBill();
                                    workBill.setAttachbilltimes(0);
                                    workBill.setBuildtime(new Date());
                                    workBill.setOrder(order);
                                    workBill.setCourier(courier);
                                    workBill.setRemark(order.getRemark());
                                    workBill.setSmsNumber("111");
                                    workBill.setPickstate("新单");
                                    workBill.setType("新");
                                    
                                    //保存工单
                                    workBillRepository.save(workBill);
                                    
                                  //设置为自动下单
                                    order.setOrderType("自动下单");
                                    return;
                                }
                            }
                        }
                        
                    }
                }
                
                
            }
            
            order.setOrderType("人工下单");
        }
    }
    
        

}
  
