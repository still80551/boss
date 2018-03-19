package com.still.bos.service.bos.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.SubareaRepository;
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

}
  
