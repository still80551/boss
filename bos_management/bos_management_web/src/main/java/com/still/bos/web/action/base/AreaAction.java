package com.still.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
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
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.bos.domain.base.Area;
import com.still.bos.domain.base.Standard;
import com.still.bos.service.bos.base.AreaService;
import com.still.bos.web.action.CommonAction;
import com.still.utils.PinYin4jUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午9:11:49 <br/>       
 */


@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends CommonAction<Area>{

   
        public AreaAction() {

          super(Area.class);
}
    
    // 使用属性驱动获取用户上传的文件
    private File file;
    public void setFile(File file) {
        this.file = file;
    }
    
    
    @Autowired
    private AreaService areaService;
    
    @Action(value = "areaAction_importXLS" ,results = {@Result(name="success" ,location="/pages/base/area.html"
            ,type = "redirect")})
    public String importXLS(){
        
        
        try {
            ArrayList<Area> list = new ArrayList<>();
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
         // 读取第一个工作簿
            HSSFSheet sheet = hssfWorkbook.cloneSheet(0);
            for (Row row : sheet) {
                
                // 跳过第一行
                if(row.getRowNum()==0){
                    continue;
                }
                // 读取数据
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();
                
                // 截掉最后一个字符
                province = province.substring(0,province.length()-1);
                city = city.substring(0,city.length()-1);
                district = district.substring(0,district.length()-1);
                
             // 获取城市编码  例如 深圳---SHENZHEN
                String citycode =
                        PinYin4jUtils.hanziToPinyin(city, "").toUpperCase();
                
             // 获取简码   例如河北石家庄长安----HBSJZCA
                String[] headByString = PinYin4jUtils
                        .getHeadByString(province + city + district);
                String shortcode =
                        PinYin4jUtils.stringArrayToString(headByString);
                
                Area area = new Area();
                area.setProvince(province);
                area.setCity(city);
                area.setDistrict(district);
                area.setPostcode(postcode);
                area.setCitycode(citycode);
                area.setShortcode(shortcode);
                
                list.add(area);
            }
            
            areaService.save(list);
            hssfWorkbook.close();
           
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return SUCCESS;
        
    }
    
      @Action(value="areaAction_pageQuery")
      public String pageQuery() throws IOException{
          
       Pageable pageable = new PageRequest(page-1, rows);
      Page<Area> page =  areaService.findAll(pageable);
       
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.setExcludes(new String[]{"subareas"});
      
       page2json(page,jsonConfig);
       
          return NONE;
          
      }
 
      private String q;
      public void setQ(String q) {
        this.q = q;
    }
      
      
      @Action(value = "areaAction_findAll")
      public String findAll() throws IOException{
          
         List<Area> list;
         
         if(StringUtils.isNotEmpty(q)){
             list=areaService.findByQ(q);
         }else{
             Page<Area> page = areaService.findAll(null);
             list = page.getContent();
         }
         
         JsonConfig jsonConfig = new JsonConfig();
         jsonConfig.setExcludes(new String[] { "subareas" });
         list2json(list, jsonConfig);
         
         
        return NONE;
          
      }

      
      
      
}
  
