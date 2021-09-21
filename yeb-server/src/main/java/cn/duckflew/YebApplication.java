package cn.duckflew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.duckflew.mapper")
public class YebApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run((YebApplication.class));
    }
}
