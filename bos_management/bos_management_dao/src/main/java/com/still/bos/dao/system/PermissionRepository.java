package com.still.bos.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 上午9:55:44 <br/>       
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
  
