package cn.duckflew.controller;


import cn.duckflew.pojo.Menu;
import cn.duckflew.pojo.MenuRole;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.Role;
import cn.duckflew.service.IAdminRoleService;
import cn.duckflew.service.IMenuRoleService;
import cn.duckflew.service.IMenuService;
import cn.duckflew.service.IRoleService;
import cn.duckflew.service.impl.MenuRoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/sys/basic")
public class RoleController {


    @Autowired
    IRoleService roleService;
    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody  Role role)
    {
        if (role.getName().startsWith("ROLE_"))
        {
            roleService.save(role);
            return RespBean.success("添加角色成功");
        }
        else RespBean.error("角色名不符合规范");
        return RespBean.error("添加失败");
    }
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid )
    {
        boolean success = roleService.removeById(rid);
        if (success)
        return RespBean.success("删除角色成功");
        return RespBean.error("删除失败");
    }
    @ApiOperation(value = "更新角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role)
    {
        boolean success = roleService.updateById(role);
        if (success)
        return RespBean.success("更新角色成功");
        return RespBean.error("更新失败");
    }
    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> roleList()
    {
       return   roleService.list();
    }



    @Autowired
    IMenuService menuService;
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menu")
    public List<Menu>getAllMenus()
    {
        return menuService.getAllMenus();
    }

    @Autowired
    IMenuRoleService menuRoleService;
    @ApiOperation(value = "根据角色id查询菜单ids 查询的RoleMenu表")
    @GetMapping("/mid/{rid}")
    public List<Integer> menuIds(@PathVariable Integer rid)
    {
        List<MenuRole> menuRoles = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid));
        List<Integer> menuIds = menuRoles.stream().map(MenuRole::getMid).collect(Collectors.toList());
        return menuIds;
    }

    @ApiOperation(value = "更新角色的菜单")
    @PutMapping("/role/menu")
    public RespBean updateMenuRole(Integer rid,Integer[]mids)
    {
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
