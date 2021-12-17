package com.briup.cms.mapper.extend;

import com.briup.cms.entity.Role;

import java.util.List;

/**
 * @Auther: vanse
 * @Date: 2021/12/13-12-13-21:17
 * @Description：com.briup.cms.mapper.extend
 * @version：1.0
 */
public interface RoleExtendMapper {
    List<Role> findAll();
    Role findByName(String name);
}
