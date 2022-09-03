package cn.duckflew.service;

import cn.duckflew.pojo.Department;
import cn.duckflew.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    RespBean addDepartment(Department department);

    RespBean delDep(Integer id);
}
