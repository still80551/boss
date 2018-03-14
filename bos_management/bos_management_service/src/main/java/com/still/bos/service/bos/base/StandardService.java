package com.still.bos.service.bos.base;

import org.springframework.transaction.annotation.Transactional;

import com.still.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午3:59:41 <br/>       
 */

@Transactional
public interface StandardService {

    void save(Standard standard);

}
  
