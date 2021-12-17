package com.briup.cms.service;

import java.util.List;


import com.briup.cms.entity.Category;
import com.briup.cms.utils.PageUtil;

public interface CategoryService {
	//分页获取所有分类 并按序号升序
	PageUtil<Category> findAll(PageUtil<Category> pageUtil);
	//新增分类或者更新分类
	void saveCategory(Category category);
	void updateCategory(Category category);
	//批量删除分类
	void deleteCategoryInBatch(List<Integer> ids);
	//更新分类序号
	void updateCategoryNo(Integer id, Integer no);
}