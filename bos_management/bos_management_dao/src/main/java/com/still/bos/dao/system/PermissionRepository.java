package com.still.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.still.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 上午9:55:44 <br/>       
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("select p from Permission p inner join p.roles r inner join r.users u where u.id = ?")
    List<Permission> findbyUid(Long id);

}
  
