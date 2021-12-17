package com.briup.cms.controller;

import com.briup.cms.entity.Role;
import com.briup.cms.service.RoleService;
import com.briup.cms.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("role")
@Api(tags = "角色模块")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@PostMapping("save")
	@ApiOperation(value = "添加角色")
	public Result save(@RequestBody Role role) {
		roleService.save(role);
		return Result.success();
	}
	
	@GetMapping("findAll")
	@ApiOperation(value = "查询所有")
	public Result findAll() {
		return Result.success(roleService.findAll());
	}
	
	@DeleteMapping("delete/{id}")
	@ApiOperation(value = "删除角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int", paramType = "path")
	})
	public Result delete(@PathVariable Integer id) {
		roleService.delete(id);
		return Result.success();
	}	
}