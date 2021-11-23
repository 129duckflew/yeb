package cn.duckflew.config.security;

import cn.duckflew.pojo.Admin;
import cn.duckflew.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    IAdminService adminService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring()
                .antMatchers(
                    "/doc",
                        "/css/**",
                        "/js/**",
                        "/index.html",
                        "favicon.ico",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-resources",
                        "/v2/api-docs/**",
                        "/login",
                        "/logout",
                        "/captcha"
                 );
    }

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;
    @Autowired
    CustomerFilter customerFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //jwt不需要csrf
        http.csrf().disable()
        //Session相关的关小
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>()
                {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object)
                    {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customerFilter);
                        return object;
                    }
                })
                .and()
                .headers()
                .cacheControl();
        //Jwt过滤器
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //自定义未授权未登录处理
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService()
    {
       return username-> {
           Admin admin = adminService.getAdminByUsername(username);
           if (null!=admin)
           {
               admin.setRoles(adminService.getRoles(admin.getId()));
               return  admin;
           }
           throw new UsernameNotFoundException("用户名或者密码不正确");
       };
    }
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter()
    {
        return new JwtAuthenticationFilter();
    }
}
