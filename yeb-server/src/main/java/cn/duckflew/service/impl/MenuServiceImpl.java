package cn.duckflew.service.impl;

import cn.duckflew.mapper.MenuMapper;
import cn.duckflew.pojo.Admin;
import cn.duckflew.pojo.Menu;
import cn.duckflew.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.Collections;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public List<Menu> getMenusByAdminId()
    {
        Admin admin =(Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = admin.getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus=( List<Menu>)valueOperations.get("menu_"+id);
        if (CollectionUtils.isEmpty(menus))
        {
            menus = menuMapper.getMenusByAdminId(id);
            valueOperations.set("menu_"+id,menus);
        }
        return menus;
    }

    /**
     * 根据角色请求菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole()
    {
        return menuMapper.getMenusWithRole();
    }
}
