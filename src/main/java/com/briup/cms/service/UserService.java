package com.briup.cms.service;

import com.briup.cms.entity.User;
import com.briup.cms.entity.extend.UserExtend;
import com.briup.cms.utils.PageUtil;

import java.util.List;

/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-13:53
 * @Description：com.briup.cms.service
 * @version：1.0
 */
public interface UserService {
    // 插入用户
    void insert(User user);
    // 查找用户详情(回显数据)
    User findById(int id);
    //分页获取所有用户信息
    PageUtil<UserExtend> getAll(PageUtil<UserExtend> page);
    //更新学生信息
    void update(User user);
    // 删除单个用户
    void deleteById(Integer id);
    //批量删除用户信息
    void deleteUserInBatch(List<Integer> ids);
    //修改用户的状态
    void updateUserStatus(Integer id,Integer status);
    //登录
    User login(String username,String password);
    //根据用户名获取用户信息
    User findUserByUsername(String username);
}
