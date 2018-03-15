package com.still.bos.service.bos.base.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctc.wstx.util.StringUtil;
import com.still.bos.dao.base.CourierRepository;
import com.still.bos.domain.base.Courier;
import com.still.bos.domain.base.Standard;
import com.still.bos.service.bos.base.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:18:11 <br/>       
 */

@Service
@Transactional
public class CourierServiceImpl implements CourierService{

    @Autowired
    private CourierRepository courierRepository;
    
    @Override
    public void save(Courier model) {
          
        courierRepository.save(model);
        
    }

    @Override
    public Page<Courier> findAll(Pageable pageable) {
          
        return courierRepository.findAll(pageable);
    }

    @Override
    public void batchDel(String ids) {
        // 判断数据是否为空
        if(StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            for (String string : split) {
                courierRepository.updateDelTagById(Long.parseLong(string));
            }
        }
        
    }

}
  
