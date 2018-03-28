package com.still.bos.service.bos.take_delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.take_delivery.WayBillRepository;
import com.still.bos.domain.take_delivery.WayBill;
import com.still.bos.service.bos.take_delivery.WayBillService;

/**  
 * ClassName:WayBillServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午7:54:36 <br/>       
 */

@Service
@Transactional
public class WayBillServiceImpl implements WayBillService {

    
    @Autowired
    private WayBillRepository wayBillRepository;

    @Override
    public void save(WayBill model) {
          
        wayBillRepository.save(model);
    }
    
    
    
    
    
}
  
