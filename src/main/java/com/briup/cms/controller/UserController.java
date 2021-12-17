package com.briup.cms.controller;

import com.briup.cms.entity.User;
import com.briup.cms.entity.extend.UserExtend;
import com.briup.cms.service.UserService;
import com.briup.cms.utils.PageUtil;
import com.briup.cms.utils.Result;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-13:52
 * @Description：com.briup.cms.controller
 * @version：1.0
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户模块") //区分模块
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 插入用户
     * 使用rest风格传参
     * 前端向后端传递json格式数据,后端向前端返回json格式数据
     */
    @ApiOperation(value = "添加用户", notes = "json传参")
    @PostMapping("insert")
    public Result insert(@RequestBody User user) {
        // 将来前端传用户数据
        userService.insert(user);
        return Result.success();
    }

    /**
     * 查找用户详情   (暂时前端不需要使用查看详情  回显数据可以时使用插槽处理)
     * 访问规则: http://localhost:8989/user/info/4
     */
    @ApiOperation(value = "查看用户详情", notes = "rest传参 用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    })
    @GetMapping("info/{id}")
    public Result info(@PathVariable("id") int id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

    @ApiOperation(value = "分页查找用户", notes = "提供分页条件")
    @PostMapping("findByPage")
    public Result findByPage(@RequestBody PageUtil<UserExtend> pageUtil) {
        pageUtil= userService.getAll(pageUtil);
        return Result.success(pageUtil);
    }

    @ApiOperation(value = "修改用户", notes = "json传参")
    @PutMapping("update")
    public Result update(@RequestBody User user) {
        // 将来前端传用户数据
        userService.update(user);
        return Result.success();
    }

    @ApiOperation(value = "将用户禁用", notes = "rest传参")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "用户状态", required = true, dataType = "int", paramType = "path")
    })
    @PutMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable("id") int id,@PathVariable("status") int status) {
        // 将来前端传用户数据
        userService.updateUserStatus(id,status);
        return Result.success();
    }

    @ApiOperation(value = "删除单个用户",notes = "rest风格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteBatch(@PathVariable("id") int id) {
        userService.deleteById(id);
        return Result.success();
    }

    @ApiOperation(value = "批量删除",notes = "需要传递多个id")
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@ApiParam(value = "批量id")@RequestBody List<Integer> ids) {
        // @ApiParam 默认是json格式
        userService.deleteUserInBatch(ids);
        return Result.success();
    }


}
