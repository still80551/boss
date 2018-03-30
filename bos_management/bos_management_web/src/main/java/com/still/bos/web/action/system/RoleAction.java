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

import com.still.bos.domain.system.Permission;
import com.still.bos.domain.system.Role;
import com.still.bos.service.bos.system.RoleService;
import com.still.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;

/**  
 * ClassName:RoleAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午1:03:35 <br/>       
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class RoleAction extends CommonAction<Role>{

    public RoleAction() {
        super(Role.class);  
    }

    private String menuIds;
    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
    private Long[] permissionIds;   
    public void setPermissionIds(Long[] permissionIds) {
        this.permissionIds = permissionIds;
    }
    
    @Autowired
    private RoleService roleService;
    
    //保存角色
    @Action(value = "roleAction_save", results = {@Result(name = "success",
    location = "/pages/system/role.html", type = "redirect")})
    public String save() throws IOException {
        
        roleService.save(getModel(),menuIds,permissionIds);
        
        return SUCCESS;
        
    }
    
    
    //权限分页查询
    @Action(value = "roleAction_pageQuery")
    public String pageQuery() throws IOException {
        
        Pageable pageable = new PageRequest(page-1, rows);

        Page<Role> page = roleService.findAll(pageable);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"permissions","users","menus"});

        page2json(page, jsonConfig);
        
        return NONE;
        
    }
    
    

    //查询所有角色
    @Action(value = "roleAction_findAll")
    public String findAll() throws IOException {

        Page<Role> page = roleService.findAll(null);
        System.out.println(page.getContent()+"------------");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"users","permissions","menus"});

        list2json(page.getContent(), jsonConfig);
        
        return NONE;
        
    }
    
    
    
    
    
    
    
}
  
