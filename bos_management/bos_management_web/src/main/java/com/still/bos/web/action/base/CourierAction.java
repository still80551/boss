package com.still.bos.web.action.base;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.bos.domain.base.Courier;
import com.still.bos.service.bos.base.CourierService;

/**  
 * ClassName:CourierAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:05:44 <br/>       
 */


@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier>{

    private Courier Model = new Courier();
    
    
    @Override
    public Courier getModel() {
          
        return Model;
    }
    
    @Autowired
    private CourierService courierService;
    
    @Action(value = "courierAction_save" ,results = {@Result(name="success" ,location="/pages/base/courier.html"
            ,type = "redirect")})
    public String save(){
        
        courierService.save(Model);
        return SUCCESS;
        
    }
    
    

}
  
