package com.still.bos.service.bos.base.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.CourierRepository;
import com.still.bos.dao.base.FixedAreaRepository;
import com.still.bos.dao.base.SubareaRepository;
import com.still.bos.dao.base.TakeTimeRepository;
import com.still.bos.domain.base.Courier;
import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;
import com.still.bos.domain.base.TakeTime;
import com.still.bos.service.bos.base.FixedAreaService;

/**  
 * ClassName:FixedAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午5:00:11 <br/>       
 */

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {

    
    @Autowired
     private FixedAreaRepository fixedAreaRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    
    @Override
    public void save(FixedArea model) {

        fixedAreaRepository.save(model);
    }

    @Override
    public Page<FixedArea> findAll(Pageable pageable) {
          
        return fixedAreaRepository.findAll(pageable);
    }

    @Override
    public void associationCourierToFixedArea(Long fixedAreaId, Long courierId, Long takeTimeId) {
          
     // 持久态的对象
        FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
        Courier courier = courierRepository.findOne(courierId);
        TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
        
        courier.setTakeTime(takeTime);
        fixedArea.getCouriers().add(courier);
        
    }

     @Autowired
     private SubareaRepository subareaRepository;
    
    @Override
    public void assignSubarea2FixedArea(Long fixedAreaId, Long[] subareaIds) {
        FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
        Set<SubArea> subareas = fixedArea.getSubareas();
        for (SubArea subArea : subareas) {
            subArea.setFixedArea(null);
        }
        
        for (Long subareaId : subareaIds) {
            
            SubArea subArea = subareaRepository.findOne(subareaId);
            subArea.setFixedArea(fixedArea);
        }
       }
    
        
        
    }


  
