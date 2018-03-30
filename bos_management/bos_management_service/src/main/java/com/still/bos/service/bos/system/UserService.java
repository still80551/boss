package com.still.bos.service.bos.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.still.bos.domain.system.User;

/**  
 * ClassName:UserService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午5:34:06 <br/>       
 */
public interface UserService {

    void save(User model, Long[] roleIds);

    Page<User> findAll(Pageable pageable);

}
  
