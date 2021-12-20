package com.briup.cms.mapper.extend;

import com.briup.cms.entity.Category;
import com.briup.cms.utils.PageUtil;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: vanse
 * @Date: 2021/12/16-12-16-14:28
 * @Description：com.briup.cms.mapper.extend
 * @version：1.0
 */
public interface CategoryExtendMapper {
    Page<Category> findAll(Map params);
    Category findByName(String name);
    List<Category> findByParentId(Integer id);
    List<Category> parentCategory();
}
