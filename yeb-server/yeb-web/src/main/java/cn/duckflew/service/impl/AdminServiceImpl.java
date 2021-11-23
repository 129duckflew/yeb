package cn.duckflew.service.impl;

import cn.duckflew.config.security.JwtTokenUtil;
import cn.duckflew.mapper.AdminMapper;
import cn.duckflew.mapper.AdminRoleMapper;
import cn.duckflew.mapper.RoleMapper;
import cn.duckflew.pojo.Admin;
import cn.duckflew.pojo.AdminRole;
import cn.duckflew.pojo.RespBean;
import cn.duckflew.pojo.Role;
import cn.duckflew.service.IAdminService;
import cn.duckflew.utils.AdminUtils;
import cn.duckflew.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duckflew
 * @since 2021-03-24
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleMapper roleMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public RespBean login(String username, String password,String code, HttpServletRequest req)
    {
        /**
         * 校验验证码
         */
        String captcha = (String) req.getSession().getAttribute("captcha");
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        System.out.println(code);
        System.out.println("==============================session中的验证码"+captcha+"========================================================");
        if (StringUtil.isEmpty(captcha)||!captcha.equalsIgnoreCase(code))
        {
            return  RespBean.error("验证码输入错误 请重新输入");
        }
        /**
         * 登录
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword()))
        {
            return RespBean.error("用户名或者密码不正确");
        }
        if (!userDetails.isEnabled())
        {
            return RespBean.error("账户已经被禁用,清联系管理员");
        }
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        //更新security 登录用户对象  放在Spring 全文中
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Map<String,String> tokenMap=new HashMap<>();
        /**
         * 返回token相关的数据
         */
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }


    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin getAdminByUsername(String username)
    {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    @Override
    public List<Role> getRoles(Integer adminId)
    {

        return roleMapper.getRoles(adminId);
    }

    //获取所有操作员
    @Autowired
    AdminUtils adminUtils;
    @Override
    public List<Admin> getAllAdmins(String keywords)
    {
        Admin currentAdmin = adminUtils.getCurrentAdmin();
        Integer currentAdminId = currentAdmin.getId();
        return adminMapper.getAllAdmins(currentAdminId,keywords);
    }


    @Autowired
    AdminRoleMapper adminRoleMapper;
    //更新操作员的角色
    @Transactional
    @Override
    public RespBean updateAdminRoles(Integer adminId, Integer[] rids)
    {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer res = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length==res)return RespBean.success("更新成功");
        return RespBean.error("更新失败");
    }

}
