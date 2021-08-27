package cn.duckflew.controller;


import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.RespPageBean;
import cn.duckflew.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duckflew
 * @since 2021-08-27
 */
@Api(tags = "员工管理接口")
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
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println(employee.toString());
        for (LocalDate localDate : beginDateScope)
        {
            System.out.println(localDate);
        }
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        return employeeService.getEmpByPage(currentPage,pageSize,employee,beginDateScope);

    }

}
