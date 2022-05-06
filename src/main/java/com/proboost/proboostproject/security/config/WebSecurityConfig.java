package com.proboost.proboostproject.security.config;


import com.proboost.proboostproject.Services.UserService;
import com.proboost.proboostproject.filter.CustomAthorizationFilter;
import com.proboost.proboostproject.filter.CustomAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.authorizeRequests().antMatchers("/login","/api/users/refresh_token","/api/v1/**").permitAll() ;
      http.authorizeRequests().antMatchers(HttpMethod.GET,"/offre").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/entretien").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/entretien").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/entretien").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/qcm").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

       /*http
            .csrf().disable()
             .authorizeRequests()
             .antMatchers("/**")
             .permitAll()
             .anyRequest()
              .authenticated().and()
               .formLogin();*/

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
