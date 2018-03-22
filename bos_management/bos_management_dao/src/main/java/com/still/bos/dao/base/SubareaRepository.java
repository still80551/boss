package com.still.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午9:05:46 <br/>       
 */
public interface SubareaRepository extends JpaRepository<SubArea, Long>{

    
    //查询未关联定区的分区
    List<SubArea> findByFixedAreaIsNull();

    List<SubArea> findByFixedArea(FixedArea fixedArea);

}
  
