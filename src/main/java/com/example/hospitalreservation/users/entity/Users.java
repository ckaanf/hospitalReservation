package com.example.hospitalreservation.users.entity;

import com.example.hospitalreservation.reservation.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  String password;

    @JsonBackReference
    @OneToMany(mappedBy = "users")
    private final List<Reservation> reservations = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles = new ArrayList<>();

    public Users(String username, String name, String password, List<UserRole> userRoles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
    }
    public void addUserRole(UserRole userRole){
        this.userRoles.add(userRole);
    }

    public static Users of(String username, String name, String password, List<UserRole> userRoles){
        return new Users(username, name, password, userRoles);
    }
}
