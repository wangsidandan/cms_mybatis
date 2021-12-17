package com.briup.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.briup.cms.exception.CustomerException;
import com.briup.cms.mapper.ArticleMapper;
import com.briup.cms.mapper.extend.ArticleExtendMapper;
import com.briup.cms.utils.PageUtil;
import com.briup.cms.utils.ResultCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.cms.entity.Article;
import com.briup.cms.service.ArticleService;
import org.springframework.util.StringUtils;

import javax.lang.model.type.ArrayType;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleExtendMapper articleExtendMapper;

    @Override
    public void save(Article article) {
        String title = article.getTitle();
        if (!StringUtils.hasText(title)) {
            throw new CustomerException(ResultCode.PARAM_IS_BLANK);
        }
        Article articleFromDB = articleExtendMapper.findByTitle(title);
        if (articleFromDB != null) {
            throw new CustomerException(ResultCode.DATA_USEING);
        }
        article.setPublishTime(new Date());
        article.setReadTimes(0);
        article.setThumbDown(0);
        article.setThumbUp(0);
        article.setStatus(0);
        articleMapper.insertSelective(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public PageUtil<Article> findAll(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPageNumber(), pageUtil.getPageSize());
        Page all = articleExtendMapper.findAll(pageUtil.getParams());
        pageUtil.setList(all.getResult());
        pageUtil.setTotal(all.getTotal());
        return pageUtil;
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteArticleInBatch(List<Integer> ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void updateArticleStatus(Integer id, Integer status) {
			Article article = new Article();
			article.setId(id);
			article.setStatus(status);
			articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<Article> findByCategoryId(Integer id) {
        return articleExtendMapper.findByCategoryId(id);
    }
}
