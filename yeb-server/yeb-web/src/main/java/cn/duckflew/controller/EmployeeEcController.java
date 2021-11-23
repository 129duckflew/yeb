package cn.duckflew.controller;


import cn.duckflew.pojo.EmployeeEc;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IEmployeeEcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2021-03-24
 */
@RestController
@Api
@RequestMapping("/per/ec/")
public class EmployeeEcController {

    @Autowired
    IEmployeeEcService ecService;


    @ApiOperation(value = "查询单个用户的奖惩记录")
    @GetMapping("/{eid}")
    public List<EmployeeEc> getEmpEc(@PathVariable Integer eid)
    {
        return ecService.list(new QueryWrapper<EmployeeEc>().eq("eid",eid));
    }

    @ApiOperation(value = "查询所有奖惩记录")
    @GetMapping("/")
    public List<EmployeeEc> getEmpEc()
    {
        return ecService.list();
    }
    @ApiOperation(value = "删除一条奖惩记录")
    @DeleteMapping("/{id}")
    public RespBean deleteEmpEc(@PathVariable Integer id)
    {

        boolean success = ecService.removeById(id);
        if (success)return  RespBean.success("删除成功");
        else return RespBean.error("删除失败");
    }
    @ApiOperation(value = "新增一条奖惩记录")
    @PostMapping("/")
    public RespBean addEmpEc(@RequestBody EmployeeEc employeeEc)
    {
        employeeEc.setEcDate(LocalDate.now());
        boolean success = ecService.save(employeeEc);
        if (success)return  RespBean.success("新增成功");
        else return RespBean.error("新增失败");
    }
}

