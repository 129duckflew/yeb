package cn.duckflew.controller;

import cn.duckflew.pojo.Menu;
import cn.duckflew.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/system/basic/")
@Api
public class PermissionController
{
    @Autowired
    IMenuService menuService;
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menu")
    public List<Menu> getAllMenus()
    {
        return menuService.getAllMenus();
    }
}
