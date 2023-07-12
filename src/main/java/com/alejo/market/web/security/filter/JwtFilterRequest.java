package com.alejo.market.web.security.filter;

import com.alejo.market.domain.service.ApiUserDetailsService;
import com.alejo.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ApiUserDetailsService apiUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Capturamos el encabezado de la petición y obtenermos el header Authorization
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) { //Validamos que viene un JWT además debemos utilizar el prefijo Bearer como es un JWT.
            //Capturamos el JWT partimos de la posión porque hay que descontar el espacio y los espacios de Bearer
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);//Obtenermos el usuario del token

            //Validamos que el usuario no se allá logueado en nuestra aplicación. Para verificar que en el contexto aun no exista una autenticación para este usuario.
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               //Obetener el detalle de los usuarios
                UserDetails userDetails = apiUserDetailsService.loadUserByUsername(username);

                //Para preguntar si el JWT es correcto
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    //Levantamos la sessión para ese usuario.
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Con el fin de validar que navegador esta usando, aque horario se conecto, que sistema operativo tenia.


                    SecurityContextHolder.getContext().setAuthentication(authToken); //Asignamos la autenticación para que la proximas vez no tenga que pasar por toda la validación de este filtro.

                }
            }
        }

        filterChain.doFilter(request, response); //Indicamos que el filtro sea evaludado con filterChain.
    }
}
