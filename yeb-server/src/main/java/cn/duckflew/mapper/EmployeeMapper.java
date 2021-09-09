package cn.duckflew.mapper;

import cn.duckflew.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author duckflew
 * @since 2021-08-27
 */
public interface EmployeeMapper extends BaseMapper<Employee>
{

    IPage<Employee> getEmpByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    IPage<Employee> getEmpWithSalary(Page<Employee> page);
}
