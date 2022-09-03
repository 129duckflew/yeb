package cn.duckflew.controller;


import cn.duckflew.pojo.Department;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IDepartmentService;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;
    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }


    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department)
    {
        return departmentService.addDepartment(department);
    }
    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean delDep(@PathVariable Integer id)
    {
        return departmentService.delDep(id);
    }
}
