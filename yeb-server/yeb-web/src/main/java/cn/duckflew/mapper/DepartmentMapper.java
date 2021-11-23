package cn.duckflew.mapper;

import cn.duckflew.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface DepartmentMapper extends BaseMapper<Department>
{

    List<Department> getAllDepartments(int parentId);

    void addDepartment(@Param("department") Department department);

    void delDep(@Param("department") Department department);
}
