package cn.duckflew.exception;

import cn.duckflew.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException sqlException)
    {
        if (sqlException instanceof SQLIntegrityConstraintViolationException)
        {
            return RespBean.error("本数据有关联的数据 无法删除");
        }
        return RespBean.error("数据库异常");
    }
}
