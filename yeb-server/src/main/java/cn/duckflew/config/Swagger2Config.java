package cn.duckflew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config
{

    @Bean
    public Docket createApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.duckflew.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("办公项目接口文档")
                .description("接口文档")
                .contact(new Contact(
                        "duckflew","http://localhost:8081/doc.html","129duckflew@gmail.com"
                ))
                .version("1.0")
                .build();
    }
}
