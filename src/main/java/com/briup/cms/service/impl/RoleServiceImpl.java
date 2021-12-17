package com.briup.cms.service.impl;

import java.util.List;

import com.briup.cms.exception.CustomerException;
import com.briup.cms.mapper.RoleMapper;
import com.briup.cms.mapper.extend.RoleExtendMapper;
import com.briup.cms.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.cms.entity.Role;
import com.briup.cms.service.RoleService;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleExtendMapper roleExtendMapper;

    @Override
    public List<Role> findAll() {
        return roleExtendMapper.findAll();
    }

    @Override
    public void save(Role role) {
        String name = role.getName();
        if (!StringUtils.hasText(name)) {
			throw new CustomerException(ResultCode.PARAM_IS_BLANK);
        }
		Role roleFromDB = roleExtendMapper.findByName(name);
        if(roleFromDB != null){
			throw new CustomerException(ResultCode.DATA_EXISTED);
		}
        roleMapper.insertSelective(role);
    }

    @Override
    public void delete(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
