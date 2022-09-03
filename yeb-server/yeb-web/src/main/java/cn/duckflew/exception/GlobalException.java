package cn.duckflew.exception;

import cn.duckflew.pojo.RespBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        sqlException.printStackTrace();
        return RespBean.error("数据库异常",sqlException.getMessage());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public RespBean UsernameNotFoundException(UsernameNotFoundException usernameNotFoundException)
    {
        RespBean error = RespBean.error(usernameNotFoundException.getMessage());
        error.setCode(403);
        return error;
    }
}
