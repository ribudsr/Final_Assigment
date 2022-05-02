package com.apigateway.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.apigateway.apigateway.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests() .antMatchers("/", "/login", "/oauth/**").permitAll()
	 * .anyRequest().authenticated() .and() .formLogin().permitAll() .and()
	 * .oauth2Login() .loginPage("/login") .userInfoEndpoint()
	 * .userService(oauthUserService).and() .successHandler(new
	 * AuthenticationSuccessHandler() {
	 * 
	 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
	 * HttpServletResponse response, Authentication authentication) throws
	 * IOException, ServletException {
	 * 
	 * CustomOAuth2User oauthUser = (CustomOAuth2User)
	 * authentication.getPrincipal();
	 * 
	 * 
	 * response.sendRedirect("/list"); } }); }
	 */
     
    @Autowired
    private CustomOAuth2UserService oauthUserService;
     
 
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    
}
