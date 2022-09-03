package cn.duckflew.mapper;

import cn.duckflew.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单
     * @param mids
     * @return
     */
    int insertRecord(@Param("mids") Integer[] mids, @Param("rid") Integer rid);
}
