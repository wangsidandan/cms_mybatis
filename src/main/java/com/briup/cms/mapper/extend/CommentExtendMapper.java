package com.briup.cms.mapper.extend;

import com.briup.cms.entity.Comment;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @Auther: vanse
 * @Date: 2021/12/17-12-17-15:55
 * @Description：com.briup.cms.mapper.extend
 * @version：1.0
 */
public interface CommentExtendMapper {
    Page<Comment> findAll(Map<String,Object> params);
}
