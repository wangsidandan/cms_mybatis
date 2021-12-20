package com.briup.cms.entity.extend;

import com.briup.cms.entity.Article;
import com.briup.cms.entity.Category;
import com.briup.cms.entity.User;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/19-12-19-21:53
 * @Description：com.briup.cms.entity.extend
 * @version：1.0
 */
@Data
public class ArticleExtend extends Article {
    private User user;
    private Category category;
}
