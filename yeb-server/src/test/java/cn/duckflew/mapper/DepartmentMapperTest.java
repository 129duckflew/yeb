package cn.duckflew.mapper;

import cn.duckflew.pojo.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DepartmentMapperTest
{

    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    void getAllDepartments()
    {
        List<Department> allDepartments = departmentMapper.getAllDepartments(-1);
        if (allDepartments!=null&&allDepartments.size()>0) System.out.println("有数据");
    }
}