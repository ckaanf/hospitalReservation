package com.example.hospitalreservation.config.security.dto;

import com.example.hospitalreservation.users.constant.RoleType;
import com.example.hospitalreservation.users.entity.UserRole;
import com.example.hospitalreservation.users.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public record CustomUserDetails(
        Long userId,
        String username,

        String name,
        String password,
        Collection<? extends GrantedAuthority> authorities

) implements UserDetails {

    public static CustomUserDetails of(Long userId, String username, String name, String password, List<UserRole> userRoles){
        return new CustomUserDetails(
                userId,
                username,
                name,
                password,
                userRoles.stream()
                        .map(UserRole::getRole)
                        .map(RoleType::getType)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static CustomUserDetails from(Users users){
        return CustomUserDetails.of(
                users.getId(),
                users.getUsername(),
                users.getName(),
                users.getPassword(),
                users.getUserRoles()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
