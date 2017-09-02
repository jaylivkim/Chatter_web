package com.hanium.chatter.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsServiceImpl; 
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("1123").roles("ADMIN");
    }
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/registration", "/all").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/userLogin")
                .defaultSuccessUrl("/lobby")
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/")
                .permitAll();
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsServiceImpl);
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web
    		.ignoring()
    			.antMatchers("/resources/**", "/static/**", "/webjars/**", "/js/**", "/css/**", "/images/**");
    }
}
