package com.still.bos.web.action.system;

import java.io.IOException;

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

import com.still.bos.domain.system.Menu;
import com.still.bos.domain.system.Permission;
import com.still.bos.service.bos.system.PermissionService;
import com.still.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;

/**  
 * ClassName:PermissionAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 上午9:50:22 <br/>       
 */


@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class PermissionAction extends CommonAction<Permission>{

    public PermissionAction() {
          
        super(Permission.class);  
        
    }
    
    
    @Autowired
    private PermissionService permissionService;

  //保存权限
    @Action(value = "permissionAction_save", results = {@Result(name = "success",
            location = "/pages/system/permission.html", type = "redirect")})
    public String save() {
        
        permissionService.save(getModel());
        
        
        return SUCCESS;
        
    }
    
    //权限分页查询
    @Action(value = "permissionAction_pageQuery")
    public String pageQuery() throws IOException {
        
        Pageable pageable = new PageRequest(page-1, rows);

        Page<Permission> page = permissionService.findAll(pageable);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"roles"});

        page2json(page, jsonConfig);
        
        
        return NONE;
        
    }
    
    //查询所有权限
    @Action(value = "permissionAction_findAll")
    public String findAll() throws IOException {

        Page<Permission> page = permissionService.findAll(null);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"roles"});

        list2json(page.getContent(), jsonConfig);
        
        return NONE;
        
    }
    
    
    
    
    
    
    
    
}
  
