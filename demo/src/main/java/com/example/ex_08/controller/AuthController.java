package com.example.ex_08.controller;

import com.example.ex_08.model.entity.User;
import com.example.ex_08.model.login.JwtRequest;
import com.example.ex_08.model.login.JwtResponse;
import com.example.ex_08.security.jwt.JwtUtils;
import com.example.ex_08.security.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceImpl userDetailService;

    private final JwtUtils jwtUtils;


    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody JwtRequest jwtRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) userDetailService.loadUserByUsername(principal.getName());
    }
}
