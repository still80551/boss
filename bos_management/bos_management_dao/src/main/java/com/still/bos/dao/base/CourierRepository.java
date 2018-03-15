package com.still.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.still.bos.domain.base.Courier;

/**  
 * ClassName:courierRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:20:06 <br/>       
 */
public interface CourierRepository extends JpaRepository<Courier, Long>{

    
    @Modifying
    @Query("update Courier set deltag = 1 where id = ?")
    void updateDelTagById(long parseLong);

}
  
