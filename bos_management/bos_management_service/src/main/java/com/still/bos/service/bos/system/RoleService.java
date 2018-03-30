package com.still.bos.service.bos.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.system.Role;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午1:07:25 <br/>       
 */
public interface RoleService {

    void save(Role model, String menuIds, Long[] permissionIds);

    Page<Role> findAll(Pageable pageable);

 

}
  
