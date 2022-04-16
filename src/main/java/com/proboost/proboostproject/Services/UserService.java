package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Role;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Respositories.UserRepo;
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
}
