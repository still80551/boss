package com.still.bos.service.bos.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.bos.dao.system.MenuRepository;
import com.still.bos.domain.base.Area;
import com.still.bos.domain.system.Menu;
import com.still.bos.domain.system.User;
import com.still.bos.service.bos.system.MenuService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:50:44 <br/>       
 */

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    
    @Override
    public List<Menu> findLevelOne() {

        
        return menuRepository.findByParentMenuIsNull();
    }

    
    //保存菜单
    @Override
    public void save(Menu model) {
          
        //判断是否要添加一级菜单
        Menu parentMenu = model.getParentMenu();
        if(parentMenu != null && parentMenu.getId() == null){
            model.setParentMenu(null);
        }
        
        menuRepository.save(model);
    }

    //菜单分页查询
    @Override
    public Page<Menu> findAll(Pageable pageable) {
          
        return menuRepository.findAll(pageable);
    }


    @Override
    public List<Menu> findbyUser(User user) {
        if("admin".equals(user.getUsername())){
            return menuRepository.findAll();
        }
        
        return menuRepository.findbyUser(user.getId());
    }

}
  
