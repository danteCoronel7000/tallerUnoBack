package com.taller.bibliotecas;

import com.taller.bibliotecas.config.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BibliotecasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecasApplication.class, args);
	}

	@Configuration
    @EnableWebSecurity
	public class WebSecurityConfig {

		@Value("${jwt.secret.key}")
		private String secret;

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.headers(headers -> headers
							.frameOptions().disable()  // Desactiva el X-Frame-Options para el control por Content-Security-Policy
							.contentSecurityPolicy("frame-ancestors 'self' http://localhost:4200")  // Permite iframes solo desde localhost:4200
					)
					.addFilterAfter(new JWTAuthorizationFilter(secret), UsernamePasswordAuthenticationFilter.class)
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().authorizeRequests()
					.requestMatchers(HttpMethod.POST, "/api/**").permitAll()
					.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
					.requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
					.anyRequest().authenticated();
			return http.build();
		}

		// Agrega esta configuración de CORS
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("http://localhost:4200") // Permite el acceso desde tu app Angular
							.allowedMethods("GET", "POST", "PUT", "DELETE")
							.allowedHeaders("*");
				}
			};
		}
	}
}
//areas, editoriales, tipos,autores
