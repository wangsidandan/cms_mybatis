package com.briup.cms.service.impl;

import java.util.List;

import com.briup.cms.entity.Article;
import com.briup.cms.exception.CustomerException;
import com.briup.cms.mapper.CategoryMapper;
import com.briup.cms.mapper.extend.ArticleExtendMapper;
import com.briup.cms.mapper.extend.CategoryExtendMapper;
import com.briup.cms.utils.PageUtil;
import com.briup.cms.utils.ResultCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.cms.entity.Category;
import com.briup.cms.service.CategoryService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryExtendMapper categoryExtendMapper;
	@Autowired
	private ArticleExtendMapper articleExtendMapper;

	@Override
	public PageUtil<Category> findAll(PageUtil<Category> pageUtil) {
		PageHelper.startPage(pageUtil.getPageNumber(),pageUtil.getPageSize());
		Page<Category> all = categoryExtendMapper.findAll(pageUtil.getParams());
		pageUtil.setList(all.getResult());
		pageUtil.setTotal(all.getTotal());
		return pageUtil;
	}

	@Override
	public void saveCategory(Category category) {
		String name = category.getName();
		if(!StringUtils.hasText(name)){
			throw new CustomerException(ResultCode.PARAM_IS_BLANK);
		}
		Category categoryFromDB = categoryExtendMapper.findByName(name);
		if(categoryFromDB != null){
			throw new CustomerException(ResultCode.DATA_EXISTED);
		}
		categoryMapper.insertSelective(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public void deleteCategoryInBatch(List<Integer> ids) {
		for (Integer id : ids){
			// 看该目录是否被引用
				// select * from cms_category where parent_id = id
			// 是不是父目录
			List<Category> categoryChildren = categoryExtendMapper.findByParentId(id);
			if(!CollectionUtils.isEmpty(categoryChildren)){
				throw new CustomerException(ResultCode.DATA_USEING);
			}
			// 目录下是不是有文章
			List<Article> articleList = articleExtendMapper.findByCategoryId(id);
			if(!CollectionUtils.isEmpty(articleList)){
				throw new CustomerException(ResultCode.DATA_USEING);
			}
			categoryMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateCategoryNo(Integer id, Integer no) {
		Category category = new Category();
		category.setId(id);
		category.setNo(no);
		categoryMapper.updateByPrimaryKeySelective(category);
	}
}
