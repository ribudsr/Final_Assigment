package com.apigateway.apigateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    public CustomAuthenticationProvider() {
        super();
    }


@Override
public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String username = String.valueOf(auth.getName());
    String password = String.valueOf(auth.getCredentials().toString());
    
    final List<GrantedAuthority> grantedAuths = new ArrayList<>();
    grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    final UserDetails principal = new User(username, password, grantedAuths);
    final Authentication authentication = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    return authentication;

}



@Override
public boolean supports(Class<?> authentication) {
	// TODO Auto-generated method stub
	 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}
}
