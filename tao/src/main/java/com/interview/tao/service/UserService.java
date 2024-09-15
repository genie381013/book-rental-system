package com.interview.tao.service;

import com.interview.tao.common.UserNotFoundException;
import com.interview.tao.controller.UserResponse;
import com.interview.tao.entity.User;
import com.interview.tao.mapper.BookRentalMapper;
import com.interview.tao.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final BookRentalMapper bookRentalMapper;

    private UserService(UserRepository repository,
                        BookRentalMapper bookRentalMapper)
    {
        this.repository = repository;
        this.bookRentalMapper = bookRentalMapper;
    }

    public UserResponse verifyUser(String username, String password) throws UserNotFoundException {
        return repository.findByUsernameAndPassword(username, password)
                .map(bookRentalMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("The user is not found"));
    }

    public User getUser(UUID uuid) throws UserNotFoundException {
        return repository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("The user is not found"));
    }

    public UserResponse getUserResponse(UUID uuid) throws UserNotFoundException {
        User user = this.getUser(uuid);
        return bookRentalMapper.toUserResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user ->
                    org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                            .password(user.getPassword())
                            .authorities(user.getRole().name())
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build())
                .orElseThrow(() -> new UsernameNotFoundException("The user is not found"));
    }
}
