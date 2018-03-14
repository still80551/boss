package com.still.bos.service.bos.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.CourierRepository;
import com.still.bos.domain.base.Courier;
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

}
  
