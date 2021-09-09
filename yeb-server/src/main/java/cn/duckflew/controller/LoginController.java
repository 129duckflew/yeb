package cn.duckflew.controller;

import cn.duckflew.pojo.Admin;
import cn.duckflew.pojo.AdminLoginParam;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@CrossOrigin
@RestController
public class LoginController
{
    @Autowired
    private IAdminService adminService;
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest httpRequest)
    {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),httpRequest);
    }
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout()
    {
        return RespBean.success("注销成功!");
    }



    @GetMapping("/admin/info")
    @ApiOperation(value = "获取当前登录用户的信息")
    public Admin getAdminInfo(Principal principal)
    {
        if (principal==null) return null;
        String username=principal.getName();
        Admin admin=adminService.getAdminByUsername(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

}
