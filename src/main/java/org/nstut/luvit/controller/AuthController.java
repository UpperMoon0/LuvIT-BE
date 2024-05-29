package org.nstut.luvit.controller;

import org.nstut.luvit.dto.CommonResponse;
import org.nstut.luvit.dto.LoginRequest;
import org.nstut.luvit.dto.RegisterRequest;
import org.nstut.luvit.gender.EGender;
import org.nstut.luvit.gender.Gender;
import org.nstut.luvit.gender.GenderService;
import org.nstut.luvit.role.ERole;
import org.nstut.luvit.role.RoleService;
import org.nstut.luvit.status.EStatus;
import org.nstut.luvit.status.StatusService;
import org.nstut.luvit.user.User;
import org.nstut.luvit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final GenderService genderService;
    private final StatusService statusService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService,
                          GenderService genderService,
                          StatusService statusService,
                          RoleService roleService,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.genderService = genderService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("Register request: " + registerRequest);
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setFullName(registerRequest.getFullName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setAge(registerRequest.getAge());
        Gender gender = genderService.getGender(EGender.valueOf(registerRequest.getGender()));
        Gender preference = genderService.getGender(EGender.valueOf(String.valueOf(registerRequest.getPreference())));
        newUser.setGender(gender);
        newUser.setPreference(preference);
        newUser.setStatus(statusService.getStatus(EStatus.ENABLED));
        newUser.setRole(roleService.getRole(ERole.ROLE_USER));
        newUser.setCreatedAt(LocalDateTime.now());

        userService.save(newUser);

        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("User registered successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login request: " + loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a token. The implementation of this method depends on the type of token you are using.
        String token = "dummy-token";

        // Return a success response with the token
        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("User logged in successfully");
        response.setData(token);

        return ResponseEntity.ok(response);
    }
}