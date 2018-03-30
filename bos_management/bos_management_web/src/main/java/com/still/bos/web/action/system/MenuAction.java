package com.still.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.still.bos.domain.base.Area;
import com.still.bos.domain.system.Menu;
import com.still.bos.service.bos.system.MenuService;
import com.still.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;



/**  
 * ClassName:MenuAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:31:52 <br/>       
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class MenuAction extends CommonAction<Menu>{

    @Autowired
    private MenuService menuService;
    
    public MenuAction() {
          
        super(Menu.class);  
        
    }
    
    //找出一级菜单
    @Action(value = "menuAction_findLevelOne")
    public String findLevelOne() throws IOException {
        
        List<Menu> list = menuService.findLevelOne();
        
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"roles","childrenMenus","parentMenu"});

         list2json(list, jsonConfig);
        
        return NONE;
        
    }
    
    //保存菜单
    @Action(value = "menuAction_save", results = {@Result(name = "success",
            location = "/pages/system/menu.html", type = "redirect")})
    public String save() {
        
        menuService.save(getModel());
        
        return SUCCESS;
        
        
    }
    
    //菜单分页查询
    @Action(value = "menuAction_pageQuery")
    public String pageQuery() throws IOException {
        
        Pageable pageable = new PageRequest(Integer.parseInt(getModel().getPage())-1, rows);

        Page<Menu> page = menuService.findAll(pageable);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"roles","childrenMenus","parentMenu"});

        page2json(page, jsonConfig);

        return NONE;
        
    }

}
  
