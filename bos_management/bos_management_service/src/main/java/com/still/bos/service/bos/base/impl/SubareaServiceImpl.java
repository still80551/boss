package com.still.bos.service.bos.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.SubareaRepository;
import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;
import com.still.bos.service.bos.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午8:58:17 <br/>       
 */

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{

    @Autowired
    private SubareaRepository subareaRepository;
    
    @Override
    public void save(SubArea model) {
          
        subareaRepository.save(model);
    }

    @Override
    public Page<SubArea> findAll(Pageable pageable) {
        
        return subareaRepository.findAll(pageable);
    }

    @Override
    public List<SubArea> findUnAssociatedSubarea() {
          
        return subareaRepository.findByFixedAreaIsNull();
    }

    @Override
    public List<SubArea> findAssociatedSubarea(Long fixedAreaId) {
          
        FixedArea fixedArea = new FixedArea();
        fixedArea.setId(fixedAreaId);
        
        return subareaRepository.findByFixedArea(fixedArea);
    }

}
  
