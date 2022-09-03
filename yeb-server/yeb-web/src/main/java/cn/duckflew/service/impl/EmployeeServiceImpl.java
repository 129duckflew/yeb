package cn.duckflew.service.impl;

import cn.duckflew.mapper.EmployeeMapper;
import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.RespPageBean;
import cn.duckflew.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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


    @Override
    public RespBean getWorkId()
    {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(WorkID)"));
        Map<String, Object> stringObjectMap = maps.get(0);
        Integer maxWorkId= Integer.parseInt(stringObjectMap.get("max(WorkID)").toString())+1;
        String res = String.format("%08d", maxWorkId);
        return RespBean.success("查询成功",res);
    }

    /**
     * 处理合同期限 保留两位小数
     * @param employee
     * @return
     */

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public RespBean addEmp(Employee employee)
    {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat=new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        int insert = employeeMapper.insert(employee);
        if (insert==1)
        {
            Employee employee1 = employeeMapper.selectById(employee.getId());
            rabbitTemplate.convertAndSend("mail.welcome",employee1);
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespPageBean getEmpWithSalary(Integer currentPage, Integer pageSize)
    {
          Page<Employee> page=new Page<>(currentPage,pageSize);
         IPage<Employee> employeeIPage = employeeMapper.getEmpWithSalary(page);
        return    new RespPageBean(employeeIPage .getTotal(),employeeIPage .getRecords());
    }
}
