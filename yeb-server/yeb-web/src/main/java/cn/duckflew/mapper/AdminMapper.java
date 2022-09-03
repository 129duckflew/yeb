package cn.duckflew.mapper;

import cn.duckflew.pojo.Admin;
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
public interface AdminMapper extends BaseMapper<Admin>
{


    List<Admin> getAllAdmins(@Param("currentAdminId") Integer currentAdminId, @Param("keywords") String keywords);
}
