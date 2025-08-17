package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.security.auth.login.CredentialException;

import com.example.config.JwtProvider;
import com.example.exception.UserException;
import com.example.model.User;
import com.example.model.Varification;
import com.example.repository.UserRepository;
import com.example.request.LoginRequest;
import com.example.request.LoginWithGooleRequest;
import com.example.response.AuthResponse;
import com.example.service.CustomUserDetailsServiceImplementation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data

@RestController
@RequestMapping("/auth")
@Tag(name="Authentication Management", description = "Endpoints for user authentication and authorization")
public class AuthController {
@Autowired
	private UserRepository userRepository;
@Autowired
	private PasswordEncoder passwordEncoder;
@Autowired
	private JwtProvider jwtProvider;
@Autowired

	private CustomUserDetailsServiceImplementation customUserDetails;

@PostMapping("/signup")
public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException{
	
  	String email = user.getEmail();
    String password = user.getPassword();
    String fullName=user.getFullName();
    String birthDate=user.getBirthDate();
    
    User isEmailExist=userRepository.findByEmail(email);

    
    if (isEmailExist!=null) {

    	
        throw new UserException("Email Is Already Used With Another Account");
    }

    // Create new user
	User createdUser= new User();
	createdUser.setEmail(email);
	createdUser.setFullName(fullName);
    createdUser.setPassword(passwordEncoder.encode(password));
    createdUser.setBirthDate(birthDate);
    createdUser.setVerification(new Varification());
    
    User savedUser= userRepository.save(createdUser);
    
    Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    String token = jwtProvider.generateToken(authentication);

    AuthResponse authResponse= new AuthResponse(token,true);
	
    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);

}
@PostMapping("/signin")
public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

    String username = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    
    System.out.println(username +" ----- "+password);
    
    Authentication authentication = authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    
    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse= new AuthResponse();
	
	authResponse.setStatus(true);
	authResponse.setJwt(token);
	
    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
}


private Authentication authenticate(String username, String password) {
    UserDetails userDetails = customUserDetails.loadUserByUsername(username);
    
    System.out.println("sign in userDetails - "+userDetails);
    
    if (userDetails == null) {
    	System.out.println("sign in userDetails - null " + userDetails);
        throw new BadCredentialsException("Invalid username or password");
    }
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
    	System.out.println("sign in userDetails - password not match " + userDetails);
        throw new BadCredentialsException("Invalid username or password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
}
}





	
	
	