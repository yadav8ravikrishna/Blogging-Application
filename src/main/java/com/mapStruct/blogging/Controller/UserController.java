package com.mapStruct.blogging.Controller;

import com.mapStruct.blogging.dto.AuthRequest;
import com.mapStruct.blogging.dto.UserDto;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.service.UserServiceInterface;
import com.mapStruct.blogging.service.serviceImplementation.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceInterface userServiceInterface;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserServiceInterface userServiceInterface, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userServiceInterface = userServiceInterface;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDto> getAllUsers() {
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public UserDto getUser(@PathVariable Long userId) throws ResourceNotFoundException {
        return userServiceInterface.getUser(userId);
    }

    @PostMapping("/addUser")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        return userServiceInterface.addUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long userId) throws ResourceNotFoundException {
        return userServiceInterface.deleteUser(userId);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws ResourceNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getUserName());
            } else {
                throw new BadCredentialsException("invalid user request !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("username or password is wrong");
        }
    }

    @GetMapping(value = "/username")
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
}
