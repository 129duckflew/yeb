package cn.duckflew.controller;


import cn.duckflew.pojo.EmployeeTrain;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IEmployeeTrainService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
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
@Api
@RequestMapping("/per/train")
public class EmployeeTrainController {

    @Autowired
    IEmployeeTrainService employeeTrainService;


    @ApiOperation(value = "获取员工的培训信息")
    @GetMapping("/{empId}")
    public List<EmployeeTrain> getEmpTrains(@PathVariable Integer empId)
    {
        return employeeTrainService.list(new QueryWrapper<EmployeeTrain>().eq("eid",empId));
    }
    @ApiOperation(value = "新增一条培训记录")
    @PostMapping("/")
    public RespBean addEmpTrain(@RequestBody EmployeeTrain employeeTrain)
    {
        employeeTrain.setFinish(false);
        boolean success = employeeTrainService.save(employeeTrain);
        if (success)
        {
            return RespBean.success("添加记录成功");
        }
        else
        {
            return   RespBean.error("添加记录失败");
        }
    }
    @ApiOperation(value = "修改一条培训记录")
    @PutMapping("/")
    public RespBean updateEmpTrain(@RequestBody EmployeeTrain employeeTrain)
    {
        boolean success = employeeTrainService.updateById(employeeTrain);
        if (success)
        {
            return RespBean.success("修改记录成功");
        }
        else
        {
            return   RespBean.error("修改记录失败");
        }
    }
}
