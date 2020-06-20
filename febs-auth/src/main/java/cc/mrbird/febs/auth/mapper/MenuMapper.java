package cc.mrbird.febs.auth.mapper;

import cc.mrbird.febs.common.entity.system.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-21 15:21
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过用户名查找用户权限集合
     * @param username
     * @return
     */
    List<Menu> findUserPermissions(String username);
}
