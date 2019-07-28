package main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//resource in htis context is API to do the crud operations. to access the user must be authenticated.
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Configuration
    @EnableResourceserver

    public static final String RESOURCE_ID= "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new
        OAuth2AccessDeniedHandler());
    }

}
