package com.briup.cms.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/13-12-13-16:12
 * @Description：com.briup.cms.vo
 * @version：1.0
 */
@Data
public class UserVo {
    @ApiModelProperty(name = "username",value = "用户名",example = "vanse")
    private String username;
    @ApiModelProperty(name = "password",value = "密码",example = "vanse")
    private String password;
}
