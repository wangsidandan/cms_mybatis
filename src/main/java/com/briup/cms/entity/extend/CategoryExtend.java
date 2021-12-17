package com.briup.cms.entity.extend;

import com.briup.cms.entity.Category;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/16-12-16-14:37
 * @Description：com.briup.cms.entity.extend
 * @version：1.0
 */
@Data
public class CategoryExtend extends Category {
    // 所在一级分类
    private Category category;
}
