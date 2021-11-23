package cn.duckflew.utils;

import cn.duckflew.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminUtils
{
    public Admin getCurrentAdmin()
    {
        return (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
