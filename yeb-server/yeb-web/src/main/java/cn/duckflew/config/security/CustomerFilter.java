package cn.duckflew.config.security;

import cn.duckflew.pojo.Menu;
import cn.duckflew.pojo.Role;
import cn.duckflew.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.access.SecurityConfig;
import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求Url分析所需的角色
 */
@Component
public class CustomerFilter implements FilterInvocationSecurityMetadataSource
{

    @Autowired
    IMenuService menuService;


    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        FilterInvocation filterInvocation=(FilterInvocation)object;
        String requestUrl = filterInvocation.getRequestUrl();
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu : menus)
        {
            if (antPathMatcher.match(menu.getUrl(),requestUrl))
            {
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        //没匹配上的Url默认登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return false;
    }
}
