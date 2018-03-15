package com.still.bos.service.bos.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.base.Courier;
import com.still.bos.domain.base.Standard;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:15:41 <br/>       
 */
public interface CourierService {

    void save(Courier model);

    Page<Courier> findAll(Pageable pageable);

    void batchDel(String ids);

}
  
