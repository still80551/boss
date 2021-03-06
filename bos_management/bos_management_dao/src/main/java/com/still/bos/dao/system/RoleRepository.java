package com.still.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.still.bos.domain.system.Role;

/**  
 * ClassName:RoleRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午1:08:55 <br/>       
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r inner join r.users u where u.id = ?")
    List<Role> findbyUid(Long id);


}
  
