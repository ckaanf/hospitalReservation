package com.example.hospitalreservation.users.entity;

import com.example.hospitalreservation.users.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
    private RoleType role;

    @JsonBackReference
    @ManyToOne
    private Users users;

    public static UserRole of(RoleType roleType, Users users){
        return new UserRole(null, roleType, users);}
}

