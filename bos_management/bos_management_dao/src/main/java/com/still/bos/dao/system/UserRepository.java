package com.still.bos.dao.system;

import com.still.bos.domain.system.User;

/**  
 * ClassName:UserRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午9:20:30 <br/>       
 */
public interface UserRepository {

    User findByUsername(String username);

}
  
