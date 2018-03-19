package com.still.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.still.bos.domain.base.SubArea;
import com.still.bos.service.bos.base.SubareaService;
import com.still.bos.web.action.CommonAction;

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
    
    
    
    

}
  
