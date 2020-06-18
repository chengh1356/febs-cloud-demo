package cc.mrbird.febs.auth.service;

import cc.mrbird.febs.common.entity.FebsAuthUser;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FebsAuthUser user = new FebsAuthUser();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));
        //1.获取用户名和密码
        //2.isEnabled方法用于判断用户是否可用。
        //3.isAccountNonExpired用于判断账号是否过期
        //4.isCredentialsNonExpired用于判断用户凭证是否过期,即密码是否未过期
        //5.isAccountNonLocked方法用于判断账户是否未锁定
        //6.getAuthorities获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象；
        return new User(username,user.getPassword(),user.isEnabled(),
                user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
