package warsztaty.spring.ailleron.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("{noop}test").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}superuser").roles("ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
                .anyRequest().permitAll().and().csrf().disable().formLogin();

    }
}
