package ru.hse.kw;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ru.hse.kw.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
public class UiApplication {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.anyRequest().authenticated()
					.and()
					.formLogin()
					.and()
					.httpBasic().disable()
					.csrf().disable()
					.httpBasic()
					.and()
					.formLogin()
					.loginProcessingUrl("/")
					.permitAll().and()/*
					.and()
					.headers()
					.frameOptions()
					.disable()
					.and()*/
					.authorizeRequests()
					.antMatchers("/").permitAll()
					.anyRequest().authenticated();
		}
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	@Configuration
	protected static class DefaultView extends WebMvcConfigurerAdapter {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("forward:/sign-in-sign-up.html");
			registry.addViewController("/home").setViewName( "forward:/pages/index.html" );
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
			super.addViewControllers(registry);
		}

		public static void main(String[] args) {
			SpringApplication.run(UiApplication.class, args);
		}

	}
	@Service
	protected static class AuthenticationService implements UserDetailsService {
		@Override
		public UserDetails loadUserByUsername(String username)
				throws UsernameNotFoundException {
			User userInfo = new User(1,"vasya", "21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5", null);
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(userInfo.getLogin(),
					userInfo.getPassword(), Arrays.asList(authority));
			return userDetails;
		}
	}
}
