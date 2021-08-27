package cn.duckflew.controller;


import cn.duckflew.pojo.Admin;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "获取所有操作员 或者搜索操作员 keywords传入null就是获取所有")
    public List<Admin> admins(String keywords)
    {
        return adminService.getAllAdmins(keywords);
    }
    @PutMapping("/")
    @ApiOperation(value = "更新操作员")
    public RespBean updateAdmin(@RequestBody Admin admin)
    {
        if (adminService.updateById(admin))
            return RespBean.success("更新成功");
        return RespBean.error("更新失败");
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "更新操作员")
    public RespBean updateAdmin(@PathVariable Integer id)
    {
        if (adminService.removeById(id))
            return RespBean.success("删除成功");
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRoles(Integer adminId,Integer[] rids)
    {
        return adminService.updateAdminRoles(adminId,rids);
    }

}
