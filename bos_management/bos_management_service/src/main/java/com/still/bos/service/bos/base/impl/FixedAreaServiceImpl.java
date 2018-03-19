package com.still.bos.service.bos.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.FixedAreaRepository;
import com.still.bos.domain.base.FixedArea;
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
    
    @Override
    public void save(FixedArea model) {

        fixedAreaRepository.save(model);
    }

}
  
