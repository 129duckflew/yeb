package cn.duckflew.service.impl;

import cn.duckflew.mapper.DepartmentMapper;
import cn.duckflew.pojo.Department;
import cn.duckflew.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDepartments()
    {
        return departmentMapper.getAllDepartments(-1);
    }
}
