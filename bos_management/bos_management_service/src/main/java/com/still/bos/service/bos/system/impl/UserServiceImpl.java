package com.still.bos.service.bos.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.system.RoleRepository;
import com.still.bos.dao.system.UserRepository;
import com.still.bos.domain.system.Role;
import com.still.bos.domain.system.User;
import com.still.bos.service.bos.system.UserService;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午5:34:36 <br/>       
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public void save(User model, Long[] roleIds) {

        userRepository.save(model);
        if(roleIds != null && roleIds.length>0){
            for (Long roleId : roleIds) {
                Role role = roleRepository.findOne(roleId);
                model.getRoles().add(role);
                
            }
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
          
        return userRepository.findAll(pageable);
    }

}
  
