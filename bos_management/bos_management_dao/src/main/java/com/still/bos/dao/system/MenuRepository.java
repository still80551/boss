package com.still.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.bos.domain.system.Menu;

/**  
 * ClassName:MenuRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:53:45 <br/>       
 */
public interface MenuRepository extends JpaRepository<Menu, Long>{

    List<Menu> findByParentMenuIsNull();

}
  
