package com.briup.cms.config;

import com.briup.cms.interceptors.JwtInteceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInteceptors())
		.addPathPatterns("/resource/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//映射所有路径
				.allowedOrigins("*")//运行所有客户端访问
				.allowCredentials(false)//不允许携带cookie
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")//运行所有请求头字段
				.maxAge(3600);//允许客户端缓存“预检请求”中获取的信息，3600秒
	}
}