package cn.duckflew.controller;

import cn.duckflew.pojo.Admin;
import cn.duckflew.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController
{
    @Autowired
    IAdminService adminService;
    @GetMapping("/admin")
    public List<Admin> getChatAdmin(String keywords)
    {
       return adminService.getAllAdmins(keywords);
    }
}
