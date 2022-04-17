package com.proboost.proboostproject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UDecoder;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAthorizationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //||request.getServletPath().equals("/offre/all")
        //|| request.getServletPath().startsWith("/api/v1/registration")
        if(request.getServletPath().equals("/login")  || request.getServletPath().equals("/api/users/refresh_token")  || request.getServletPath().startsWith("/") ){
            String authotizationHeader = request.getHeader(AUTHORIZATION);
            System.out.println("no authorization header");
            if(authotizationHeader != null && authotizationHeader.startsWith("Bearer ")){
                System.out.println("hello tst "+authotizationHeader);
//                 try {
//
//                    String token = authotizationHeader.substring("Bearer ".length());
//                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                    JWTVerifier verifier = JWT.require(algorithm).build();
//                    DecodedJWT decodedJWT = verifier.verify(token);
//                    String username = decodedJWT.getSubject();
//                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
//                     System.out.println("L1 pass succefully");
//
//                     Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//                    stream(roles).forEach(role ->{
//                        authorities.add(new SimpleGrantedAuthority(role));
//                    });
//                     System.out.println("L2 pass succefully");
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
//                     System.out.println("L3 pass succefully");
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                     System.out.println("L4 pass succefully");
//
//                     System.out.println("L5 pass succefully");
//                     filterChain.doFilter(request,response);
//                }catch (Exception exception){
//                    log.error("Error logging in : {}",exception.getMessage());
//                    response.setHeader("error",exception.getMessage());
//                    response.setStatus(FORBIDDEN.value());
//                    Map<String ,String > error = new HashMap<>();
//                    error.put("error_message",exception.getMessage());
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    new ObjectMapper().writeValue(response.getOutputStream(),error);
//
//                }

            }

            filterChain.doFilter(request,response);
        }else{

            String authotizationHeader = request.getHeader(AUTHORIZATION);
            System.out.println("hello tst "+authotizationHeader);
            if(authotizationHeader != null && authotizationHeader.startsWith("Bearer ")){
                try {
                    String token = authotizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role ->{
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    log.error("Error logging in : {}",exception.getMessage());
                    response.setHeader("error",exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String ,String > error = new HashMap<>();
                    error.put("error_message",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);

                }

            }else {
                filterChain.doFilter(request,response);

            }
        }

    }
}
