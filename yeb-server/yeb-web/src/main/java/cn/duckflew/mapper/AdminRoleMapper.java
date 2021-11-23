package cn.duckflew.mapper;

import cn.duckflew.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole>
{

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
