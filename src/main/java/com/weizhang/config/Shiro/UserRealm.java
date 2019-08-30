package com.weizhang.config.Shiro;


import com.weizhang.model.User;
import com.weizhang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-----------执行登录认证----------");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userService.getUserByName(token.getUsername());
        if(user == null){
            throw new AccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();

        if(user == null){
            return null;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("order:index");

        // 角色与权限字符串集合
//        Collection<String> rolesCollection = new HashSet<>();
//        Collection<String> premissionCollection = new HashSet<>();
//        // 读取并赋值用户角色与权限
//        List<Role> roles = user.getRoles();
//
//
//        info.add("order:index");
//
//        for(Role role : roles){
//            rolesCollection.add(role.getName());
//            List<Permission> permissions = role.getPermissions();
//            for (Permission permission : permissions){
//                premissionCollection.add(permission.getUrl());
//            }
//            info.addStringPermissions(premissionCollection);
//        }
//        info.addRoles(rolesCollection);
        return info;

    }

}
