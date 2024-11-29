package com.RBAC.demo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminResource() {
        return "Admin resource accessed!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String userResource() {
        return "User resource accessed!";
    }

}
