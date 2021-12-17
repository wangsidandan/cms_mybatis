package com.briup.cms.controller;

import java.awt.print.PageFormat;
import java.util.List;

import com.briup.cms.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.cms.entity.Comment;
import com.briup.cms.service.CommentService;
import com.briup.cms.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "添加评论",notes = "参数为json格式")
	@PostMapping
	public Result save(@RequestBody Comment comment) {
		commentService.saveComment(comment);
		return Result.success();
	}

	// 分页查询
	@ApiOperation(value = "分页查询评论", notes = "提供当前页码和每页显示条数")
	@PostMapping("findAll")
	public Result findAll(@RequestBody PageUtil pageUtil) {
		return Result.success(commentService.findAll(pageUtil));
	}
	// 批量删除
	@ApiOperation(value = "批量删除评论", notes = "提供id集合")
	@DeleteMapping
	public Result deleteBatch(
			@ApiParam("ids")@RequestBody List<Integer> ids) {
		commentService.deleteCommentInBatch(ids);
		return Result.success();
	}
}
