package cn.duckflew.service.impl;

import cn.duckflew.mapper.MenuMapper;
import cn.duckflew.pojo.Menu;
import cn.duckflew.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
