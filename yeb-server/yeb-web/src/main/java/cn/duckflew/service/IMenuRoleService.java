package cn.duckflew.service;

import cn.duckflew.pojo.MenuRole;
import cn.duckflew.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
