package com.mictmr.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String FROM_USER_ROLES = "select username, role from user_roles where username=?";
	private static final String FROM_USERS = "select username,password, enabled from users where username=?";
	@Autowired
	DataSource dataSource;
	
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		
		
		http
            .authorizeRequests()
                .antMatchers( "/home","/css/**","/fonts/**","/images/**","/js/**").permitAll()
                .antMatchers("/usuario","/usuario/**").hasRole("ADMIN")
                .antMatchers("/register","/register/**").permitAll();
                
                /*
                 * AQUI PEGA DO BANCO AS AUTORIZAÇÕES E QUE FUNCIONALIDADE PODE ACESSAR
                for (Matcher matcher : matchers) {
                    http.authorizeRequests().antMatchers(matcher.name).hasRole(matcher.roleInfo);
                }
                */
                
		http.authorizeRequests().anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").defaultSuccessUrl("/home")
                .permitAll()
                .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll()
                .and().csrf().disable();
    }

	
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(FROM_USERS)
		.authoritiesByUsernameQuery(FROM_USER_ROLES);
        
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }
}
