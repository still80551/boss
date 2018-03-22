package com.still.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.still.bos.domain.base.Courier;
import com.still.bos.domain.base.TakeTime;
import com.still.bos.service.bos.base.TakeTimeService;
import com.still.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月22日 下午4:04:25 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class TakeTimeAction extends CommonAction<TakeTime> {

    public TakeTimeAction() {
          
        super(TakeTime.class);  
    }
    
    @Autowired
    private TakeTimeService takeTimeService;
    
    @Action(value = "takeTimeAction_findAll" )
    public String findAll() throws IOException{
        
        
        List<TakeTime> list = takeTimeService.findAll();
        
        list2json(list, null);
        
        return NONE;
        
    }
    

}
  
