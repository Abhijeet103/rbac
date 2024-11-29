package com.RBAC.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // 3 types AUTO , IDENTITY , UUID
    private Long id;

    private String username  ;
    private String password ;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles ;

    User(String username , String password)
    {
        this.username =  username ;
        this.password =  password;
        this.roles = new HashSet<>();
    }
}
