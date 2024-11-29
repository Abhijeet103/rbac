package com.RBAC.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService ;

    @Autowired
    private JwtUtil jwtUtil ;

    private final PasswordEncoder passwordEncoder =   new BCryptPasswordEncoder();
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username , @RequestParam String password , @RequestParam Role role)
    {
        userService.register(username , password , role);
        return ResponseEntity.ok("User Registered  successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username , @RequestParam String password)
    {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));



//        if(!passwordEncoder.matches(user.getPassword() , passwordEncoder.encode(password)))
//        {
//            throw new RuntimeException("Invalid Password");
//        }

        String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRoles().iterator().next()));
        return ResponseEntity.ok(token);
    }

    @GetMapping("/all")
    public List<User> getall()
    {
        return userService.getAllUser();
    }


}
