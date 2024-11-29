package com.RBAC.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    private final PasswordEncoder passwordEncoder =   new BCryptPasswordEncoder();


    public void register(String username  , String password , Role role)
    {
        User user = new User(username , passwordEncoder.encode(password)) ;
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public List<User> getAllUser()
    {
      return  userRepository.findAll();
    }

    public void RemoveUserRole(String username , Role role)
    {
        User user = userRepository.findByUsername(username).orElseThrow();
        Set<Role> roles = user.getRoles();
        roles.remove(role);
    }




    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
