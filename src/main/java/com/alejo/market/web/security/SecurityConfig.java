package com.alejo.market.web.security;

import com.alejo.market.domain.service.ApiUserDetailsService;
import com.alejo.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ApiUserDetailsService userDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    /*Para que Spring controle la gestión de la autenticación.*/
    @Bean
    public AuthenticationManager authManager(HttpSecurity http)  throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .and()
                .build();
    }

    /*Para permitir conexion a la url de autenicación y las demás requieren autorización*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Sin session ya que los JWT van a administrar la session
                .and().addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class)//Le indicamos el filtro que vamos a utilizar y que tipo de filtro es, en este caso es un filtro de usario y contraseña.
                .build();
        //Para indicar que este filtro será encargado de recibir todas la peticiones y procesarlas. .and.SessionManagmen

    }

}
