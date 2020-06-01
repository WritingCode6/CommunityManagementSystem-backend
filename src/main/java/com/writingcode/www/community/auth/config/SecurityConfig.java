package com.writingcode.www.community.auth.config;



import com.writingcode.www.community.auth.filter.CustomAuthenticationFilter;
import com.writingcode.www.community.auth.handler.CustomAccessDeniedHandler;
import com.writingcode.www.community.auth.handler.CustomHttp401AuthenticationEntryPoint;
import com.writingcode.www.community.auth.properties.SecurityProperties;
import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.auth.source.IPermissionPath;
import com.writingcode.www.community.auth.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomHttp401AuthenticationEntryPoint customHttp401AuthenticationEntryPoint;
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private JwtUtil jwtUtil;
    private IDataStore dataStore;
    private IPermissionPath permissionPath;
    private SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        if(permissionPath.ignorePath() != null){
            permissionPath.ignorePath().forEach(ignoring::antMatchers);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager(), dataStore, jwtUtil, securityProperties))
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customHttp401AuthenticationEntryPoint);

        http
                .headers().xssProtection().xssProtectionEnabled(true);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
        permissionPath.permitPath().forEach(path -> authorizeRequests.antMatchers(path).permitAll());
        permissionPath.authenticatedPath().forEach(path -> authorizeRequests.antMatchers(path).authenticated());

    }
}
