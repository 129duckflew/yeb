package cn.duckflew;

import cn.duckflew.config.security.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ClaimsTest
{
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void testClaims()
    {
        User user = new User("duckflew", passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        String token = jwtTokenUtil.generateToken(user);
        System.out.println(token);
        String username = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println(username);
    }
}
