package com.briup.cms.mapper.extend;

import com.briup.cms.entity.User;
import com.briup.cms.entity.extend.UserExtend;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-22:16
 * @Description：拓展接口
 * @version：1.0
 */
public interface UserExtendMapper {
    User findUserByUsername(String username);
    Page<UserExtend> getAll(Map<String,Object> params);
    void deleteUserInBatch(List<Integer> ids);
}
