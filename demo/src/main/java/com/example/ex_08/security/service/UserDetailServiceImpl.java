package com.example.ex_08.security.service;

import com.example.ex_08.model.entity.User;
import com.example.ex_08.repo.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found !!");
        }
        return user;
    }
}
