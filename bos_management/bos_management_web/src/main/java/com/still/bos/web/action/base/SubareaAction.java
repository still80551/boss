package com.still.bos.web.action.base;

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

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.still.bos.domain.base.FixedArea;
import com.still.bos.domain.base.SubArea;
import com.still.bos.service.bos.base.SubareaService;
import com.still.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;

/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午8:48:56 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class SubareaAction extends CommonAction<SubArea> {

    public SubareaAction() {
          
        super(SubArea.class);  
            
    }
    @Autowired
    private SubareaService subareaService;
    
    @Action(value = "subareaAction_save" ,results = {@Result(name="success" ,location="/pages/base/sub_area.html"
            ,type = "redirect")})
    public String save(){
        
        subareaService.save(getModel());
        
        
        return SUCCESS;
        
    }
    
    @Action(value = "subareaAction_pageQuery")
    public String pageQuery() throws IOException{
        
        Pageable pageable = new PageRequest(page-1, rows);
        Page<SubArea> page =  subareaService.findAll(pageable);
            
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"subareas"});
        page2json(page, jsonConfig);
            
        
            return NONE;
        
        
    }
    
    //查询未关联定区的分区
    @Action(value = "subareaAction_findUnAssociatedSubarea" )
    public String findUnAssociatedSubarea() throws IOException{
        
        List<SubArea> list = subareaService.findUnAssociatedSubarea();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"subareas"});
        
        list2json(list, jsonConfig);
        
        return NONE;
    
    }
    
    
    
    //查询已关联定区的分区
    @Action(value = "subareaAction_findAssociatedSubarea" )
    public String findAssociatedSubarea() throws IOException{
        
        List<SubArea> list = subareaService.findAssociatedSubarea(getModel().getId());
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"subareas","couriers"});
        
        list2json(list, jsonConfig);
        
        return NONE;
    
    }

}
  
