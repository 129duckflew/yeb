package cn.duckflew.controller;


import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.RespPageBean;
import cn.duckflew.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Api
@RestController
@RequestMapping("/salary/sob/cfg")
public class SalarySobCfgController
{

    @Autowired
    IEmployeeService employeeService;

    @ApiOperation(value = "获取所有员工工资账套")
    @GetMapping("/")
    public RespPageBean getAllEmpSalary(@RequestParam(defaultValue = "1")Integer currentPage, @RequestParam(defaultValue = "10")Integer pageSize)
    {
        System.out.println(currentPage+":"+pageSize);
        return employeeService.getEmpWithSalary(currentPage,pageSize);
    }
    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateAllEmpSalary(Integer eid, Integer sid)
    {
        if (employeeService.update(new UpdateWrapper<Employee>().eq("id",eid).set("salaryId",sid)))
        {
            return  RespBean.success("更新成功");
        }
        else return RespBean.error("更新失败");
    }
}
