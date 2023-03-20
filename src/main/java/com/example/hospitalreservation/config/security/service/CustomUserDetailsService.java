package com.example.hospitalreservation.config.security.service;

import com.example.hospitalreservation.config.security.dto.CustomUserDetails;
import com.example.hospitalreservation.global.exception.BusinessLogicException;
import com.example.hospitalreservation.global.exception.ExceptionCode;
import com.example.hospitalreservation.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetail =
                userRepository.findByUsername(username)
                .map(CustomUserDetails::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        log.info("userDetail : {}", userDetail);
        return userDetail;
    }
}
