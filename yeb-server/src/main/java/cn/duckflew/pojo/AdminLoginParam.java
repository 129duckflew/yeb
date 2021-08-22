package cn.duckflew.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录的实体类  保存登录的参数
 */
@Data
@EqualsAndHashCode(callSuper =false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象",description = "管理员登录参数实体类")
public class AdminLoginParam
{
    @ApiModelProperty(value = "用户名" ,required = true)
    private String username;
    @ApiModelProperty(value = "密码" ,required = true)
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    private String code;
}
