package cn.duckflew.service.impl;

import cn.duckflew.mapper.EmployeeMapper;
import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.RespPageBean;
import cn.duckflew.service.IEmployeeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duckflew
 * @since 2021-08-27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public RespPageBean getEmpByPage(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] beginDateScope)
    {
        //开启分页
        Page<Employee>page=new Page<>(currentPage,pageSize);
        IPage<Employee> employeeIPage=employeeMapper.getEmpByPage(page,employee,beginDateScope);
        return new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
    }
}
