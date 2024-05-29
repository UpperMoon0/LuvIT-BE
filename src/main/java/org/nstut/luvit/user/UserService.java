package org.nstut.luvit.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Loading user by username: {}", username);
        UserDetails userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    LOGGER.error("User not found: {}", username);
                    return new UsernameNotFoundException("User not found");
                });
        LOGGER.info("User loaded: {}", userDetails);
        return userDetails;
    }

    public void save(User user) {
        LOGGER.info("Saving user: {}", user);
        userRepository.save(user);
        LOGGER.info("User saved: {}", user);
    }

    public List<User> getAllUsers() {
        LOGGER.info("Getting all users");
        List<User> users = userRepository.findAll();
        LOGGER.info("Users retrieved: {}", users);
        return users;
    }
}