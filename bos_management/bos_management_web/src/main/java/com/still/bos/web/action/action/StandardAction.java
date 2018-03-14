package com.still.bos.web.action.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.boot.registry.StandardServiceInitiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.bos.domain.base.Standard;
import com.still.bos.service.bos.base.StandardService;

/**  
 * ClassName:StandardAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午3:38:40 <br/>       
 */



@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard>{

    
    private Standard model = new Standard();
    
    @Override
    public Standard getModel() {
          
        return model;
    }
    
    @Autowired
    private StandardService standardService;
    
  @Action(value = "standardAction_save" ,results = {@Result(name="success" ,location="/pages/base/standard.html"
          ,type = "redirect")})
    public String save(){
        
      standardService.save(model);
        
        return SUCCESS;
        
    }
    
    
    
    
    
    
    

}
  
