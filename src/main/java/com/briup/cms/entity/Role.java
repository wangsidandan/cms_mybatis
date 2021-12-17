package com.briup.cms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("角色模块")
public class Role {
    @ApiModelProperty(value = "角色id")
    private Integer id;
    @ApiModelProperty(value = "角色描述")
    private String description;
    @ApiModelProperty(value = "角色名称",example = "admin")
    private String name;
}