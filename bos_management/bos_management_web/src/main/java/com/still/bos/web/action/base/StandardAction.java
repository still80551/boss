package com.still.bos.web.action.base;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.bos.domain.base.Standard;
import com.still.bos.service.bos.base.StandardService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    
  private int page;
  private int rows;
  public void setPage(int page) {
    this.page = page;
}
  public void setRows(int rows) {
    this.rows = rows;
}
  
    @Action(value="standardAction_pageQuery")
    public String pageQuery() throws IOException{
        
     Pageable pageable = new PageRequest(page-1, rows);
    Page<Standard> page =  standardService.findAll(pageable);
        
     long total = page.getTotalElements();  
     List<Standard> list = page.getContent();
     
     Map<String, Object> map = new HashMap<>();
     map.put("total", total);
     map.put("rows", list);
    
     String  json = JSONObject.fromObject(map).toString();
     System.out.println(json+"------------------");
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(json);
    
    
    
        return NONE;
        
    }
    @Action(value="standardAction_findAll")
    public String findAll() throws IOException{
     // 查询数据
        Page<Standard> page = standardService.findAll(null);
        // 获取页面的数据
        List<Standard> list = page.getContent();
     // 转换数据为json并传回页面
        String json = JSONArray.fromObject(list).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        return NONE;
        
    }
    
    

}
  
