package cc.mrbird.febs.auth.service;

import cc.mrbird.febs.auth.manager.UserManager;
import cc.mrbird.febs.common.entity.FebsAuthUser;
import cc.mrbird.febs.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-16 23:25
 */
@Service
public class FebsUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if(systemUser != null){
            String permissions = userManager.findUserPermissions(username);
            boolean notLocked = false;
            if(StringUtils.equals(SystemUser.STATUS_VALID,systemUser.getStatus())) {
                notLocked = true;
            }
            //1.获取用户名和密码
            //2.isEnabled方法用于判断用户是否可用。
            //3.isAccountNonExpired用于判断账号是否过期
            //4.isCredentialsNonExpired用于判断用户凭证是否过期,即密码是否未过期
            //5.isAccountNonLocked方法用于判断账户是否未锁定
            //6.getAuthorities获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象；
            FebsAuthUser authUser = new FebsAuthUser(systemUser.getUsername(),systemUser.getPassword(),true,true,true,
                    notLocked,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        }else {
            throw new UsernameNotFoundException("");
        }
        
    }
}


























