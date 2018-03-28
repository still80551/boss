package com.still.bos.service.bos.base.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.AreaRepository;
import com.still.bos.domain.base.Area;
import com.still.bos.service.bos.base.AreaService;

/**  
 * ClassName:AreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午1:04:00 <br/>       
 */

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;
    
   

    @Override
    public Page<Area> findAll(Pageable pageable) {
          
        return areaRepository.findAll(pageable);
    }

    @Override
    public List<Area> findByQ(String q) {
          
        
        q = "%" + q.toUpperCase() + "%";
        return  areaRepository.findByQ(q);
    }

    @Override
    public void save(List<Area> list) {
          
        areaRepository.save(list);  
        
    }

    
   
    
  
}
  
