package com.briup.cms.controller;

import com.briup.cms.entity.User;
import com.briup.cms.service.UserService;
import com.briup.cms.utils.JwtUtil;
import com.briup.cms.utils.Result;
import com.briup.cms.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: vanse
 * @Date: 2021/12/13-12-13-16:10
 * @Description：认证接口
 * @version：1.0
 */
@RestController
@Api(tags = "认证模块")
public class AuthController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "登录",notes = "传json")
    @PostMapping("/auth/login")
    public Result login(@RequestBody UserVo userVo){
        System.out.println("又饿没有发送士大夫精神独立房间");
       User user = userService.login(userVo.getUsername(),userVo.getPassword());
       Map<String,Object> map = new HashMap<>();
       map.put("username",user.getUsername());
       map.put("image",user.getImage());
       String token = JwtUtil.sign(String.valueOf(user.getId()), map);
       Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
       return Result.success(tokenMap);
//       String token = UUID.randomUUID().toString() + "-" + user.getId();
//       return Result.success(token);

    }
    @GetMapping("/resource/info")
    public Result info(HttpServletRequest request){
        String token = request.getHeader("token");
        // 取出token中的信息
        Map<String, Object> map = JwtUtil.getInfo(token);
        return Result.success(map);
    }

    @PostMapping("/resource/logout")
    public Result login(){
        System.out.println("去除token");
        return Result.success("退出成功");
    }
}
