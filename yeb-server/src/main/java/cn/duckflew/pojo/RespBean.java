package cn.duckflew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean
{
    private long code;
    private String msg;
    private Object obj;

    /**
     * 成功返回
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message,Object obj)
    {
        return new RespBean(200,message,obj);
    }
    public static RespBean success(String message)
    {
        return new RespBean(200,message,null);
    }
    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message)
    {
        return new RespBean(500,message,null);
    }
    public static RespBean error(String msg,Object obj)
    {
        return new RespBean(500,msg,obj);
    }


}
