package com.briup.cms.mapper.extend;

import com.briup.cms.entity.Article;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: vanse
 * @Date: 2021/12/16-12-16-21:00
 * @Description：com.briup.cms.mapper.extend
 * @version：1.0
 */
public interface ArticleExtendMapper {
    List<Article> findByCategoryId(Integer id);
    Article findByTitle(String title);
    Page<Article> findAll(Map params);
}
