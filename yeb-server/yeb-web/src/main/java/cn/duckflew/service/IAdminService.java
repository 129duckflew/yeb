package cn.duckflew.service;

import cn.duckflew.pojo.Admin;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param httpRequest
     * @return
     */
    RespBean login(String username, String password,String code, HttpServletRequest httpRequest);

    Admin getAdminByUsername(String username);

    /**
     * 根据用户id查询角色
     * @return
     */
    List<Role> getRoles(Integer adminId);

    List<Admin> getAllAdmins(String keywords);

    RespBean updateAdminRoles(Integer adminId, Integer[] rids);
}
