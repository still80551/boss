package com.still.bos.service.bos.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.still.bos.dao.system.UserRepository;
import com.still.bos.domain.system.User;

/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午8:40:47 <br/>       
 */

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;
    
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        return null;
    }
    
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        
        //根据用户名查找用户
        User user = userRepository.findByUsername(username);
        
        /**
         * @param principal 当事人,主体.通常是从数据库中查询到的用户
         * @param credentials 凭证,密码.是从数据库中查询出来的密码
         * @param realmName
         */
        if(user != null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            return info;
        }
        // 找不到 -> 抛异常
        return null;
    }

}
  
