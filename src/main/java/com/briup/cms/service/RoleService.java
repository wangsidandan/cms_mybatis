package com.briup.cms.service;

import java.util.List;


import com.briup.cms.entity.Role;

public interface RoleService {
	//分页获取所有角色信息
	List<Role> findAll();
	//新增角色信息或者更新角色信息
	void save(Role role);
	//批量删除角色信息
	void delete(Integer id);
}