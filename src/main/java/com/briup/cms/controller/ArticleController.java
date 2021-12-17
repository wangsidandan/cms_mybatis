package com.briup.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.briup.cms.utils.PageUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.cms.entity.Article;
import com.briup.cms.service.ArticleService;
import com.briup.cms.utils.Result;

@RestController
@RequestMapping("/article")
@Api(tags = "文章模块")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation(value = "新增文章")
	@PostMapping
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return Result.success();
	}
	@ApiOperation(value = "修改文章")
	@PutMapping
	public Result update(@RequestBody Article article) {
		articleService.update(article);
		return Result.success();
	}
	
	@ApiOperation(value = "分页查询文章")
	@PostMapping("findAll")
	public Result findAll(@RequestBody PageUtil pageUtil) {
		PageUtil<Article> page = articleService.findAll(pageUtil);
		return Result.success(page);
	}
	
	@ApiOperation(value = "修改文章状态", notes = "提供id和status")
	@PutMapping("{id}/{status}")
	public Result updateArticleStatus(
			@ApiParam(name = "id",value = "文章id",required = true)	@PathVariable("id")Integer id,
			@ApiParam(name = "status",value = "审核状态",required = true) @PathVariable("status") Integer status) {
		articleService.updateArticleStatus(id, status);
		return Result.success();
	}

	@DeleteMapping("delete/{id}")
	@ApiOperation(value = "删除文章")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int", paramType = "path")
	})
	public Result deleteBatch(@PathVariable Integer id) {
		articleService.deleteById(id);
		return Result.success();
	}
	
	@ApiOperation(value = "批量删除文章", notes = "提供id集合")
	@DeleteMapping
	public Result deleteBatch(@ApiParam(value = "文章id") @RequestBody List<Integer> ids) {
		articleService.deleteArticleInBatch(ids);
		return Result.success();
	}


	
}
