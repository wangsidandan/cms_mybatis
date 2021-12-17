package com.briup.cms.service.impl;


import com.briup.cms.entity.Comment;
import com.briup.cms.exception.CustomerException;
import com.briup.cms.mapper.CommentMapper;
import com.briup.cms.mapper.extend.CommentExtendMapper;
import com.briup.cms.utils.PageUtil;
import com.briup.cms.utils.ResultCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.briup.cms.service.CommentService;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentExtendMapper commentExtendMapper;
	@Override
	public PageUtil<Comment> findAll(PageUtil pageUtil) {
		PageHelper.startPage(pageUtil.getPageNumber(),pageUtil.getPageSize());
		Page<Comment> page = commentExtendMapper.findAll(pageUtil.getParams());
		pageUtil.setTotal(page.getTotal());
		pageUtil.setList(page.getResult());
		return pageUtil;
	}

	@Override
	public void saveComment(Comment comment) {
		if(!StringUtils.hasText(comment.getContent())){
			throw new CustomerException(ResultCode.PARAM_IS_BLANK);
		}
		comment.setTime(new Date());
		commentMapper.insertSelective(comment);
	}


	@Override
	public void deleteCommentInBatch(List<Integer> ids) {
		for(Integer id : ids){
			commentMapper.deleteByPrimaryKey(id);
		}
	}
}
