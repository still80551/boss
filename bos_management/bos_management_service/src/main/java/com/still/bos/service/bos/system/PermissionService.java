package com.still.bos.service.bos.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.system.Permission;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 上午9:53:07 <br/>       
 */
public interface PermissionService {

    void save(Permission model);

    Page<Permission> findAll(Pageable pageable);

}
  
