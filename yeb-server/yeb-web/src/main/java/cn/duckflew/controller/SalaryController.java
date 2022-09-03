package cn.duckflew.controller;


import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.Salary;
import cn.duckflew.service.ISalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@Api
@RequestMapping("/salary/sob")
public class SalaryController {


    @Autowired
    private ISalaryService salaryService;
    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries()
    {
        return salaryService.list();
    }

    @ApiOperation(value = "增加工资账套")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary)
    {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary))
            return RespBean.success("新增成功");
        else return RespBean.error("新增失败");
    }
    @ApiOperation(value = "修改工资账套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary)
    {
        if (salaryService.updateById(salary))
        {
            return RespBean.success("修改成功");
        } else
        {
            return RespBean.error("修改失败");
        }
    }
    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/{salaryId}")
    public RespBean deleteSalary(@PathVariable Integer salaryId)
    {
        if (salaryService.removeById(salaryId))
        {
            return RespBean.success("删除成功");
        } else
        {
            return RespBean.error("删除失败");
        }

    }

}
