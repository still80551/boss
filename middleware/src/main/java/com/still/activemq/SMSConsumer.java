package com.still.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.still.utils.SmsUtils;

/**  
 * ClassName:SMSConsumer <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午3:47:47 <br/>       
 */
@Component
public class SMSConsumer implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        
        MapMessage message = (MapMessage) msg;
        try {
            String tel = message.getString("tel");
            String code = message.getString("code");
            System.out.println(tel + "====" + code);
            SmsUtils.sendSms(tel, code);
        } catch (Exception e) {
              
            e.printStackTrace();  
            
        }
    }

}
  
