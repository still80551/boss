package com.still.bos.service.bos.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.base.TakeTimeRepository;
import com.still.bos.domain.base.TakeTime;
import com.still.bos.service.bos.base.TakeTimeService;

/**  
 * ClassName:TakeTimeServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月22日 下午4:11:36 <br/>       
 */


@Transactional
@Service
public class TakeTimeServiceImpl implements TakeTimeService {

    @Autowired
    private TakeTimeRepository takeTimeRepository;
    
    @Override
    public List<TakeTime> findAll() {

        return takeTimeRepository.findAll();
    }

}
  
