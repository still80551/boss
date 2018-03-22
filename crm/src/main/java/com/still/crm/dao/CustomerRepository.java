package com.still.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.still.crm.domain.Customer;

/**  
 * ClassName:CustomerRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月19日 上午12:06:20 <br/>       
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 查询未关联定区的客户
    List<Customer> findByFixedAreaIdIsNull();

    List<Customer> findByFixedAreaId(String fixedAreaId);

    @Modifying
    @Query("update Customer set fixedAreaId = null where fixedAreaId=?")
    void unbindByFixedAreaId(String fixedAreaId);

    @Modifying
    @Query("update Customer set fixedAreaId = ? where id=?")
    void bindFixedAreaById(String fixedAreaId, Long id);


    
    
}
  
