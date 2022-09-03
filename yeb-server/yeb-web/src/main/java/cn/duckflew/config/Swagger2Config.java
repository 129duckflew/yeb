package cn.duckflew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
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
    private List<ApiKey> securitySchemes()
    {
        /**
         * 设置请求头信息
         */
        List<ApiKey> list=new ArrayList<>();
        ApiKey apiKey=new ApiKey("Authorization","Authorization","Header");
        list.add(apiKey);
        return list;
    }

    private List<SecurityContext>securityContexts()
    {
        List<SecurityContext> res=new ArrayList<>();
        res.add(getContextByPath("/hello/.*"));
        return res;
    }

    private SecurityContext getContextByPath(String pathRegex)
    {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth()
    {
        List<SecurityReference> list=new ArrayList<>();
        AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");

        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        list.add(new SecurityReference("Authorization",authorizationScopes));
        return list;
    }
}
