package com.still.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.still.bos.domain.base.FixedArea;
import com.still.bos.service.bos.base.FixedAreaService;
import com.still.bos.web.action.CommonAction;

/**  
 * ClassName:FixedAreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午4:55:02 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class FixedAreaAction extends CommonAction<FixedArea> {

    public FixedAreaAction() {
          
        super(FixedArea.class);  
        
    }
    
    
    @Autowired
    private  FixedAreaService  fixedAreaService;
    
    @Action(value = "fixedAreaAction_save" ,results = {@Result(name="success" ,location="/pages/base/fixed_area.html"
            ,type = "redirect")})
    public String save(){
        
       
        fixedAreaService.save(getModel());
        
        
        return SUCCESS;
        
    }

}
  
