package cc.mrbird.febs.auth.manager;

import cc.mrbird.febs.auth.mapper.MenuMapper;
import cc.mrbird.febs.auth.mapper.UserMapper;
import cc.mrbird.febs.common.entity.system.Menu;
import cc.mrbird.febs.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-21 15:29
 */
@Service
public class UserManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public SystemUser findByName(String username){
        return userMapper.findByName(username);
    }

    /**
     * 根据用户名获取权限列表字符串
     * @param username
     * @return
     */
    public String findUserPermissions(String username){
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
