package cn.duckflew.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页返回对象")
public class RespPageBean
{
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "数据")
    private List<?> data;
}
