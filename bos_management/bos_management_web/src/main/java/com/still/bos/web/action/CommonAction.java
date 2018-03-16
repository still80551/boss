package com.still.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.bos.domain.base.Area;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**  
 * ClassName:CommonAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午4:12:29 <br/>       
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T>{

    private T model;
    private Class<T> clazz;
    
    public CommonAction(Class<T> clazz) {
        this.clazz = clazz;

    }
     
    @Override
    public T getModel() {
          try {
            model=clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();  
            
        }
        return model;
    }
    
    protected int page;
    protected int rows;
    public void setPage(int page) {
      this.page = page;
  }
    public void setRows(int rows) {
      this.rows = rows;
  }
    
    public void page2json(Page<T> page,JsonConfig jsonConfig) throws IOException{
        
        long total = page.getTotalElements();  
        List<T> list = page.getContent();
        
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", list);
        String json;
        if(jsonConfig != null){
              json = JSONObject.fromObject(map,jsonConfig).toString();
            
        }else{
            
              json = JSONObject.fromObject(map).toString();
        }
        
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        
    }
    
    
public void list2json(List<T> list,JsonConfig jsonConfig) throws IOException{
        
      
        String json;
        if(jsonConfig != null){
              json = JSONArray.fromObject(list,jsonConfig).toString();
            
        }else{
            
              json = JSONArray.fromObject(list).toString();
        }
        
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        
    }
    
    
    
    
    

}
  
