package com.briup.cms.service;

import java.util.List;

import com.briup.cms.utils.PageUtil;

import com.briup.cms.entity.Comment;

public interface CommentService {
	//分页获取所有评论
	PageUtil<Comment> findAll(PageUtil pageUtil);
	//新增评论或者更新评论
	void saveComment(Comment comment);
	//批量删除评论
	void deleteCommentInBatch(List<Integer> ids);
}