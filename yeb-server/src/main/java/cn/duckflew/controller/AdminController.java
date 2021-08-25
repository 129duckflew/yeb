package cn.duckflew.controller;


import cn.duckflew.pojo.Admin;
import cn.duckflew.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;
    @GetMapping("/")
    @ApiOperation(value = "获取所有操作员")
    public List<Admin> admins(String keywords)
    {
        return adminService.getAllAdmins(keywords);
    }

}
