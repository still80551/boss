package com.still.bos.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.bos.domain.base.Area;
import com.still.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月24日 下午7:40:18 <br/>       
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    


}
  
