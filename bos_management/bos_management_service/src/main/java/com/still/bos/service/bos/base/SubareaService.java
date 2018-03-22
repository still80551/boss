package com.still.bos.service.bos.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午8:57:36 <br/>       
 */
public interface SubareaService {

    void save(SubArea model);

    Page<SubArea> findAll(Pageable pageable);

    List<SubArea> findUnAssociatedSubarea();

    List<SubArea> findAssociatedSubarea(Long fixedAreaId);

}
  
