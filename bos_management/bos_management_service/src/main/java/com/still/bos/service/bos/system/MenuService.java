package com.still.bos.service.bos.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.base.Area;
import com.still.bos.domain.system.Menu;
import com.still.bos.domain.system.User;

/**  
 * ClassName:MenuService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:50:03 <br/>       
 */
public interface MenuService {

    List<Menu> findLevelOne();

    void save(Menu model);

    Page<Menu> findAll(Pageable pageable);

    List<Menu> findbyUser(User user);

}
  
