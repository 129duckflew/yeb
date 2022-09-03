package cn.duckflew.service;

import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duckflew
 * @since 2021-08-27
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmpByPage(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] beginDateScope);

    RespBean getWorkId();

    RespBean addEmp(Employee employee);

    RespPageBean getEmpWithSalary(Integer currentPage, Integer pageSize);
}
