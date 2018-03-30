package com.still.bos.service.bos.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.system.PermissionRepository;
import com.still.bos.domain.system.Permission;
import com.still.bos.service.bos.system.PermissionService;

/**  
 * ClassName:PermissionServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 上午9:53:44 <br/>       
 */

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    
    @Override
    public void save(Permission model) {
        permissionRepository.save(model);
        
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
          
        return permissionRepository.findAll(pageable);
    }

}
  
