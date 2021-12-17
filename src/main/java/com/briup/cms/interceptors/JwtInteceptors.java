package com.briup.cms.interceptors;

import com.briup.cms.utils.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// 拦截认证资源
public class JwtInteceptors implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 可以放行预检请求
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		// 从请求头中获取token
		String token = request.getHeader("token");
		System.out.println("拦截token");
		System.out.println(token);
		// 判断token是否为空 空直接抛异常 
		if(token == null) {
			throw new RuntimeException("无token,请登录");
		}
		// 校验token
		JwtUtil.checkSign(token);
//		// 取出token中的信息
//		String userId = JwtUtil.getUserId(token);
//		System.out.println(userId);
//		Map<String, Object> info = JwtUtil.getInfo(token);
//		info.forEach((k,v)->{
//			System.out.println(k+"="+v);
//		});
		// 放行
		return true;
	}
	
}