package com.briup.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.briup.cms.utils.PageUtil;
import com.github.pagehelper.Page;
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

import com.briup.cms.entity.Category;
import com.briup.cms.service.CategoryService;
import com.briup.cms.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/category")
@Api(tags = "分类模块")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation(value = "新增",notes = "json格式")
	@PostMapping("/save")
	public Result saveCategory(@RequestBody Category category) {
		categoryService.saveCategory(category);
		return Result.success();
	}
	@ApiOperation(value = "更新",notes = "json格式")
	@PutMapping("/update")
	public Result updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
		return Result.success();
	}
	
	@ApiOperation(value = "查询目录",notes = "分页并且顺序查询" )
	@PostMapping("/findAll")
	public Result findAllSortbyno(@RequestBody PageUtil<Category> pageUtil) {
		PageUtil<Category> page = categoryService.findAll(pageUtil);
		return Result.success(page);
	}
	
	@ApiOperation(value = "更新分类排序",notes = "改变排序字段")
	@PutMapping("/update/{id}/{no}")
	public Result updateCategoryNo(
			@ApiParam(name = "id",value = "分类id",required = true) @PathVariable("id") int id,
			@ApiParam(name = "no",value = "排序字段",required = true)  @PathVariable("no") int no) {
		categoryService.updateCategoryNo(id, no);
		return Result.success();
	}
	
	@ApiOperation(value = "删除目录",notes = "提供多个id,批量删除")
	@DeleteMapping("/deleteBatch")
	public Result deleteCategoryInBatch(@ApiParam("id列表")@RequestBody List<Integer>ids) {
		categoryService.deleteCategoryInBatch(ids);
		return Result.success();
	}
	
}
