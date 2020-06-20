package cc.mrbird.febs.auth.mapper;

import cc.mrbird.febs.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-21 15:20
 */
public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    SystemUser findByName(String username);
}
