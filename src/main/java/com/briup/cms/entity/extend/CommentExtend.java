package com.briup.cms.entity.extend;

import com.briup.cms.entity.Article;
import com.briup.cms.entity.Comment;
import com.briup.cms.entity.User;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/20-12-20-8:38
 * @Description：com.briup.cms.entity.extend
 * @version：1.0
 */
@Data
public class CommentExtend extends Comment {
    private Comment comment; // 父评论
    private Article article;
    private User user;
}
