package cn.duckflew.service.impl;

import cn.duckflew.mapper.EmployeeEcMapper;
import cn.duckflew.pojo.Employee;
import cn.duckflew.pojo.EmployeeEc;
import cn.duckflew.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

}
