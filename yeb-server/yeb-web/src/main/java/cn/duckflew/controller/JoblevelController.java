package cn.duckflew.controller;


import cn.duckflew.pojo.Joblevel;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duckflew
 * @since 2021-08-24
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController
{
    @Autowired
    IJoblevelService joblevelService;

    @ApiOperation(value = "新增职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel)
    {
        boolean success = joblevelService.save(joblevel);
        if (success)return RespBean.success("添加成功");
        return RespBean.error("添加失败");
    }
    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id)
    {
        boolean success = joblevelService.removeById(id);
        if (success)return RespBean.success("删除成功");
        return RespBean.error("删除失败");
    }
    @ApiOperation(value = "修改职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel)
    {
        boolean success = joblevelService.updateById(joblevel);
        if (success)return RespBean.success("修改成功");
        return RespBean.error("修改失败");
    }
    @ApiOperation(value = "获取职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels()
    {
        return joblevelService.list();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean batchDelete(Integer[] ids)
    {
        boolean success = joblevelService.removeByIds(Arrays.asList(ids));
        if (success)return RespBean.success("删除成功");
        return RespBean.error("删除失败");
    }
}
