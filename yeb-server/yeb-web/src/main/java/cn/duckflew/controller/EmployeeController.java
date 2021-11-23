package cn.duckflew.controller;


import cn.duckflew.pojo.*;
import cn.duckflew.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duckflew
 * @since 2021-08-27
 */
@Api
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;
    @ApiOperation(value = "查询所有员工(分页)  用员工名来存需要搜索的关键词" )
    @GetMapping("/")
    public RespPageBean getAllEmp(@RequestParam(defaultValue = "1")Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize,
                                  Employee employee, LocalDate[]beginDateScope)
    {
        return employeeService.getEmpByPage(currentPage,pageSize,employee,beginDateScope);

    }

    @Autowired
    IPoliticsStatusService politicsStatusService;
    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus()
    {
        return politicsStatusService.list();
    }
    @Autowired
    INationService nationService;
    @ApiOperation(value = "查询所有民族")
    @GetMapping("/nation")
    public List<Nation>getAllNation()
    {
      return   nationService.list();
    }


    @Autowired
    IJoblevelService joblevelService;
    @ApiOperation(value = "查询所有职称")
    @GetMapping("/joblevel")
    public List<Joblevel>getAllJoblevel()
    {
        return   joblevelService.list();
    }

    @Autowired
    IPositionService positionService;
    @ApiOperation(value = "查询所有职位")
    @GetMapping("/position")
    public List<Position> getAllPositions()
    {
        return   positionService.list();
    }

    @Autowired
    IDepartmentService departmentService;
    @ApiOperation(value = "查询所有部门")
    @GetMapping("/department")
    public List<Department>  getAllDepartments()
    {
     return    departmentService.getAllDepartments();
    }

    @ApiOperation(value = "查询最大工号")
    @GetMapping("/workid")
    public RespBean  getMaxWorkId()
    {
        return  employeeService.getWorkId();
    }


    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee)
    {
      return   employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee)
    {
        boolean update = employeeService.updateById(employee);
        if (update)return RespBean.success("更新成功");
        return RespBean.error("update失败");
    }
    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean updateEmp(@PathVariable Integer id)
    {
        boolean delete = employeeService.removeById(id);
        if (delete)return RespBean.success("删除成功");
        return RespBean.error("delete失败");
    }
}
