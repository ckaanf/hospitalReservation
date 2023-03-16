package com.example.hospitalreservation.users.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  String password;
    @JsonManagedReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles = new ArrayList<>();

    public Users(String userId, String name, String password, List<UserRole> userRoles) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
    }
    public void addUserRole(UserRole userRole){
        this.userRoles.add(userRole);
    }

    public static Users of(String userId, String name, String password, List<UserRole> userRoles){
        return new Users(userId, name, password, userRoles);
    }
}
