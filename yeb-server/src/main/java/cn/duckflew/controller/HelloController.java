package cn.duckflew.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
    @GetMapping("/emplyee/basic/hello")
    public String hello2()
    {
        return "/emplyee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3()
    {
        return "/employee/advanced/hello";
    }
}
