package com.briup.cms.service;

import java.util.List;


import com.briup.cms.entity.Article;
import com.briup.cms.utils.PageUtil;
import com.github.pagehelper.Page;

public interface ArticleService {
	//发布资讯或者编辑资讯
	void save(Article article);
	void update(Article article);
	//分页获取所有资讯
	PageUtil<Article> findAll(PageUtil pageUtil);
	// 删除咨询
	void deleteById(Integer id);
	//批量删除资讯
	void deleteArticleInBatch(List<Integer> ids);
	//管理员审核资讯
	void updateArticleStatus(Integer id,Integer status);
	// 查询分类下的文章
	List<Article> findByCategoryId(Integer id);
}
