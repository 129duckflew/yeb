package cn.duckflew.service.impl;

import cn.duckflew.mapper.MenuRoleMapper;
import cn.duckflew.pojo.MenuRole;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.service.IMenuRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {


    @Autowired
    MenuRoleMapper menuRoleMapper;
    @Transactional
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids)
    {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (null == mids || 0 == mids.length) return RespBean.success("更新成功");
        menuRoleMapper.insertRecord(mids, rid);
        return RespBean.success("更新成功");
    }
}
