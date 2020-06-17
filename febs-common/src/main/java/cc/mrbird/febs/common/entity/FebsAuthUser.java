package cc.mrbird.febs.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-16 23:27
 */
@Data
public class FebsAuthUser implements Serializable {

    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}
