package com.still.bos.service.bos.system.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.system.MenuRepository;
import com.still.bos.dao.system.PermissionRepository;
import com.still.bos.dao.system.RoleRepository;
import com.still.bos.domain.system.Menu;
import com.still.bos.domain.system.Permission;
import com.still.bos.domain.system.Role;
import com.still.bos.service.bos.system.RoleService;

/**  
 * ClassName:RoleServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午1:07:53 <br/>       
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void save(Role model, String menuIds, Long[] permissionIds) {
        roleRepository.save(model);
        if(StringUtils.isNotEmpty(menuIds)){
            String[] splits = menuIds.split(",");
            for (String split : splits) {
                Menu menu = menuRepository.findOne(Long.parseLong(split));
                model.getMenus().add(menu);
            }
        }
        
        if(permissionIds != null && permissionIds.length>0){
            for (Long permissionId : permissionIds) {
                Permission permission = permissionRepository.findOne(permissionId);
                model.getPermissions().add(permission);
            }
        }
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
          
        return roleRepository.findAll(pageable);
    }
    
  

}
  
