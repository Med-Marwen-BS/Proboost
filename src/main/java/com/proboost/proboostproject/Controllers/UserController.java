package com.proboost.proboostproject.Controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proboost.proboostproject.Modules.Role;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
     private final UserService userService ;

     @GetMapping("/refresh_token")
     public void Refresh_Token(HttpServletRequest request, HttpServletResponse response) throws IOException {
         String authotizationHeader = request.getHeader(AUTHORIZATION);
         if(authotizationHeader != null && authotizationHeader.startsWith("Bearer ")){
             try {
                 String refresh_token = authotizationHeader.substring("Bearer ".length());
                 Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                 JWTVerifier verifier = JWT.require(algorithm).build();
                 DecodedJWT decodedJWT = verifier.verify(refresh_token);
                 String username = decodedJWT.getSubject();
                 User user ;
                 if(userService.getUser(username).isPresent()){
                      user = userService.getUser(username).get();
                 }else {
                     throw new Exception("user not found");
                 }

                 ArrayList<Role> roles = new ArrayList<>();
                 roles.add(user.getRole());

                 String access_token = JWT.create()
                         .withSubject(user.getUsername())
                         .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                         .withIssuer(request.getRequestURL().toString())
                         //.withClaim("roles",user.getRole().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                         .withClaim("roles",roles.stream().map(Role::name).collect(Collectors.toList()))
                         .sign(algorithm);
                 Map<String ,String > tokens = new HashMap<>();
                 tokens.put("access_token",access_token);
                 tokens.put("refresh_token",refresh_token);
                 response.setContentType(APPLICATION_JSON_VALUE);
                 new ObjectMapper().writeValue(response.getOutputStream(),tokens);

             }catch (Exception exception){
                 response.setHeader("error",exception.getMessage());
                 response.setStatus(FORBIDDEN.value());
                 Map<String ,String > error = new HashMap<>();
                 error.put("error_message",exception.getMessage());
                 response.setContentType(APPLICATION_JSON_VALUE);
                 new ObjectMapper().writeValue(response.getOutputStream(),error);

             }

         }else {
             throw new RuntimeException("Refresh Token is missing");

         }
     }

     @GetMapping("/users")
    public List<User> getUSers(){
         return userService.getUsers() ;
     }

     @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String token){
         System.out.println("header = "+token);
         return new ResponseEntity<User>(userService.getCurrentUser(token), HttpStatus.OK);
     }

     @PostMapping("/toadmin/{id}")
    public User toAdmin(@PathVariable("id") int id){
            return userService.toAdmin(id);
     }

    @PostMapping("/touser/{id}")
    public User toUser(@PathVariable("id") int id){
        return userService.toUser(id);
    }
    @PostMapping("/toemployer/{id}")
    public User toEmployer(@PathVariable("id") int id){
        return userService.toEMployer(id);
    }

    @PostMapping("/locked/{id}")
    public User locked(@PathVariable("id") int id){
        return userService.locked(id);
    }
}
