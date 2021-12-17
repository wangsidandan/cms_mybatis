package com.briup.cms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel("用户模型")
public class User {
    @ApiModelProperty(name = "id",value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "birthday",value = "用户生日",example = "2000-01-01 00:00:00")
    private Date birthday;
    @ApiModelProperty(name = "gender",value = "用户性别")
    private Integer gender;
    @ApiModelProperty(name = "image",value = "用户头像")
    private String image;
    @ApiModelProperty(name = "password",value = "用户密码")
    private String password;
    @ApiModelProperty(name = "phone",value = "用户电话",example = "13500000000")
    private String phone;
    @ApiModelProperty(name = "realName",value = "真实姓名",example = "tom")
    private String realName;
    @ApiModelProperty(name = "registerTime",value = "注册时间",hidden = true)
    private Date registerTime;
    @ApiModelProperty(name = "status",value = "用户状态 有默认值",example = "0")
    private Integer status;
    @ApiModelProperty(name = "username",value = "用户姓名(不能修改)",example = "追风少年")
    private String username;
    @ApiModelProperty(name = "roleId",value = "角色id")
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}