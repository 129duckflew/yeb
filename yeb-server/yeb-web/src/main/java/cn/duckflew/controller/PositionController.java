package cn.duckflew.controller;


import cn.duckflew.pojo.Position;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("system/basic/position")
public class PositionController {

    @Autowired
    IPositionService positionService;
    @ApiOperation(value = "获取所有职位")
    @GetMapping("/")
    public List<Position> getAllPosition()
    {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position)
    {
        position.setCreateDate(LocalDateTime.now());
        boolean save = positionService.save(position);
        if (save)return RespBean.success("添加成功");
        return RespBean.success("添加失败");

    }
    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position)
    {
        boolean success = positionService.updateById(position);
        if (success)
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id)
    {
        boolean success = positionService.removeById(id);
        if (success)
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deleteMany(Integer [] ids){
        if (positionService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        else
        {
            return RespBean.error("删除失败");
        }
    }
}
