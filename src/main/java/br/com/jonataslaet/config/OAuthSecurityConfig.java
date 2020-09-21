package br.com.jonataslaet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@Bean
//	Este Bean é suprido pela linha ".secret("{noop}@ngul@r0")" em AuthorizationServerConfig.java
//	A utilização do {noop} configura o framework Spring para instanciar uma classe NoOpPasswordEncoder para ser utilizada como encoder.
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
}
