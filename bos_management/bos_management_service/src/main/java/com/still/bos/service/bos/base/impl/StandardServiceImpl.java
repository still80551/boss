package com.still.bos.service.bos.base.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.StandardRepository;
import com.still.bos.domain.base.Standard;
import com.still.bos.service.bos.base.StandardService;


/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午4:24:35 <br/>       
 */

@Transactional
@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardRepository standardRepository;
    
    @Override
    public void save(Standard standard) {
        standardRepository.save(standard);

    }

    @Override
    public Page<Standard> findAll(Pageable pageable) {
        return standardRepository.findAll(pageable);
    }

}
  
