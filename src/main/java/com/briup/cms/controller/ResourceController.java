package com.briup.cms.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource")
@Api(tags = "测试模块")
public class ResourceController {
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
}