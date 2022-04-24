package com.proboost.proboostproject.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.proboost.proboostproject.Modules.Role;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Respositories.UserRepo;
import com.proboost.proboostproject.filter.CurrentUser;
import com.proboost.proboostproject.filter.CustomAthorizationFilter;
import com.proboost.proboostproject.registration.token.ConfirmationToken;
import com.proboost.proboostproject.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User appUser) {
        boolean userExists = userRepo
                .findByEmail(appUser.getEmail())
                .isPresent();


        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            if(appUser.getEnabled())
                throw new IllegalStateException("email already taken");
            else {

                if(userRepo.findByEmail(appUser.getEmail()).get().getEnabled())
                    throw new IllegalStateException("email already taken");
                String token = UUID.randomUUID().toString();

                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        userRepo.findByEmail(appUser.getEmail()).get()
                );
//
                confirmationTokenService.saveConfirmationToken(
                        confirmationToken);

//        TODO: SEND EMAIL

                return token;
            }
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

      // appUser.setPassword(encodedPassword);
        User test = new User(appUser.getPrenom(),appUser.getNom(), appUser.getEmail(), encodedPassword, Role.USER);
       // test.setPassword(encodedPassword);
        userRepo.save(test);
//
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                test
        );
//
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
        //return new ResponseEntity<User>(appUser, HttpStatus.CREATED);
        //return "it works" ;
    }

    public int enableAppUser(String email) {
        return userRepo.enableAppUser(email);
    }

    public Optional<User> getUser(String username) {
        return userRepo.findByEmail(username);
    }
    public List<User> getUsers(){
        return userRepo.findAll() ;
    }

    public User getCurrentUser(String token){
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(token);
//        String username = decodedJWT.getSubject();
//        if(userRepo.findByEmail(username).isPresent())
//            return userRepo.findByEmail(username).get() ;
//        else throw new UsernameNotFoundException("There is no user with this email");
        String username = CustomAthorizationFilter.CurrentUser(token);
        if(!username.equals(""))
            return userRepo.findByEmail(username).get();
        else throw new IllegalStateException("you need to login ");
    }

}
