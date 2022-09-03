package cn.duckflew.service;

import cn.duckflew.pojo.Menu;
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
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenusByAdminId();

    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
