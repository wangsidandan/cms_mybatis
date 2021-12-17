package com.briup.cms.service.impl;

import com.briup.cms.entity.User;
import com.briup.cms.entity.extend.UserExtend;
import com.briup.cms.exception.CustomerException;
import com.briup.cms.mapper.extend.UserExtendMapper;
import com.briup.cms.mapper.UserMapper;
import com.briup.cms.service.UserService;
import com.briup.cms.utils.PageUtil;
import com.briup.cms.utils.ResultCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-13:53
 * @Description：com.briup.cms.service.impl
 * @version：1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;

    @Override
    public void insert(User user) {
        // 用户名和密码不能为空
        if(!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())){
            throw new CustomerException(ResultCode.PARAM_IS_BLANK);
        }
        // 用户名不能重复
        User userFromDB = findUserByUsername(user.getUsername());
        if(userFromDB != null){
            throw new CustomerException(ResultCode.DATA_EXISTED);
        }
        user.setStatus(0);
        user.setRegisterTime(new Date());
        userMapper.insert(user);
    }

    @Override
    public User findById(int id) {
        if(id <= 0){
            throw new CustomerException(ResultCode.PARAM_IS_INVALID);
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageUtil<UserExtend> getAll(PageUtil<UserExtend> pageUtil) {
        PageHelper.startPage(pageUtil.getPageNumber(),pageUtil.getPageSize());
        Map<String, Object> params = pageUtil.getParams();
        //params.put("gender",0);
        Page all = userExtendMapper.getAll(params);
        pageUtil.setList(all.getResult());
        pageUtil.setTotal(all.getTotal());
        return pageUtil;
    }

    @Override
    public void update(User user) {
        // 名字不可以修改 页面直接禁用组件即可,否则会出现和数据库同名
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteUserInBatch(List<Integer> ids) {
        // 循环调用sql
        //ids.forEach(id -> userMapper.deleteByPrimaryKey(id));
        // 批量调用sql
       userExtendMapper.deleteUserInBatch(ids);
    }

    @Override
    public void updateUserStatus(Integer id, Integer status) {
        User temp = new User();
        temp.setId(id);
        temp.setStatus(status);
        userMapper.updateByPrimaryKeySelective(temp);
    }

    @Override
    public User login(String username, String password) {
        User userFromDB = findUserByUsername(username);
        if(userFromDB == null || !password.equals(userFromDB.getPassword())){
            throw new CustomerException(ResultCode.USER_LOGIN_ERROR);
        }
        return userFromDB;
    }

    @Override
    public User findUserByUsername(String username) {
        return userExtendMapper.findUserByUsername(username);
    }
}
