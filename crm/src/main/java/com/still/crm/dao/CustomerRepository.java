package com.still.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.crm.domain.Customer;

/**  
 * ClassName:CustomerRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月19日 上午12:06:20 <br/>       
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    
    
}
  
