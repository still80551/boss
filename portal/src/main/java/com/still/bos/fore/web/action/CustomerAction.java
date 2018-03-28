package com.still.bos.fore.web.action;



import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.aliyuncs.exceptions.ClientException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.still.crm.domain.Customer;
import com.still.utils.MailUtils;
import com.still.utils.SmsUtils;

/**  
 * ClassName:CustomerAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月22日 下午9:59:27 <br/>       
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    
    private Customer model = new Customer();
    
    
    @Override
    public Customer getModel() {
          
        return model;
    }
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    //发送验证码
    @Action(value="customerAction_sendSMS")
    public String sendSMS(){
        
        //随机生成验证码
        final String code = RandomStringUtils.randomNumeric(6);
        System.out.println(code);
        //储存验证码
        ServletActionContext.getRequest().getSession().setAttribute("serverCode", code);
        //发送验证码
        
        
        jmsTemplate.send("sms",new MessageCreator() {
            
            @Override
            public Message createMessage(Session session) throws JMSException {
                
                MapMessage message = session.createMapMessage();
                message.setString("tel", model.getTelephone());
                message.setString("code", code);
                
                
                return message;
            }
        });
        /*try {
            SmsUtils.sendSms(model.getTelephone(), code);
        } catch (ClientException e) {
            e.printStackTrace();  
        }*/
        
        return NONE;
        
    }
    //属性驱动获得验证码
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
    //属性驱动获得激活码
    private String activeCode;
    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
    
    @Autowired
    private RedisTemplate<String, String > redisTemplate;
    
    
    //注册功能
    @Action(value = "customerAction_regist" ,
            results = {@Result(name="success" ,location="/signup-success.html"
    ,type = "redirect"),
            @Result(name="error" ,location="/signup-fail.html"
                    ,type = "redirect")}
            )
    public String regist(){
        
        //校验验证码
        String serverCode = (String) ServletActionContext.getRequest().getSession().getAttribute("serverCode");
        if(StringUtils.isNotEmpty(checkcode)
                &&StringUtils.isNotEmpty(serverCode)
                &&serverCode.equals(checkcode)){
           
            //注册(保存客户)
            WebClient.create("http://localhost:8380/crm/webService/customerService/save")
            .type(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .post(model);
            
            //随机生成激活码
            String activeCode = RandomStringUtils.randomNumeric(32);
            
            //储存激活码
            redisTemplate.opsForValue().set(model.getTelephone(), activeCode,1,TimeUnit.DAYS);
            
            //发送激活邮件
            String emailBody = "感谢您注册本网站的帐号，请在24小时之内点击<a href='http://localhost:8280/portal/customerAction_active.action?activeCode="
                    + activeCode + "&telephone=" + model.getTelephone()
                    + "'>本链接</a>激活您的帐号";
            MailUtils.sendMail(model.getEmail(), "激活邮件 ", emailBody );
            
            return SUCCESS;
        }
        
        return ERROR;
        
    }
        
    //激活功能
    @Action(value = "customerAction_active" ,
            results = {@Result(name="success" ,location="/login.html"
    ,type = "redirect"),
            @Result(name="error" ,location="/signup-fail.html"
                    ,type = "redirect")}
            )
    public String active(){
        
        //匹配激活码
        String serverCode = redisTemplate.opsForValue().get(model.getTelephone());
        
        
        // 激活
        if(StringUtils.isNotEmpty(serverCode) && StringUtils.isNotEmpty(activeCode) && serverCode.equals(activeCode)){
            WebClient
                .create("http://localhost:8380/crm/webService/customerService/active")
                .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .query("telephone", model.getTelephone())
                .put(null);
            return SUCCESS;
        }
        
        return ERROR;
    }
        
    
   
    
    //登陆功能
    @Action(value = "customerAction_login" ,
            results = {@Result(name="success" ,location="/index.html"
    ,type = "redirect"),
            @Result(name="error" ,location="/login.html"
                    ,type = "redirect"),
            @Result(name="unactived" ,location="/login.html"
            ,type = "redirect")}
            )
    public String login(){
        
        //从Session中获取验证码
        String serverCode = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
       
        //校验前台输入的验证码和存在session中的验证码
        //不为空且匹配成功
        if(StringUtils.isNotEmpty(serverCode)&&StringUtils.isNotEmpty(checkcode)
                &&serverCode.equals(checkcode)){
            
           //判断是否已激活
           
            //通过手机号找出这个用户
            Customer customer = WebClient
            .create("http://localhost:8380/crm/webService/customerService/isActived")
            .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .query("telephone", model.getTelephone())
            .get(Customer.class);
            
            if(customer != null &&  customer.getType() != null){
               if(customer.getType() == 1){
                   
                   //已激活
                   //拿出密码和前台校验
                   if(StringUtils.isNotEmpty(model.getPassword()) 
                           && StringUtils.isNotEmpty(customer.getPassword())
                                   &&model.getPassword().equals(customer.getPassword())){
                       
                       //把客户信息存到session中
                       ServletActionContext.getRequest().getSession().setAttribute("user", customer);
                       return SUCCESS;
                   }else{
                       return ERROR;
                   }
                   
               }
               return "unactived";
            }

        }
        
        return ERROR;
        
    }
    
    
    
}
  
