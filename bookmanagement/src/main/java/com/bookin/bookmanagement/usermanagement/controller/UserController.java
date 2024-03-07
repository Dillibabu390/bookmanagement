package com.bookin.bookmanagement.usermanagement.controller;
import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.response.APIResponseUtil;
import com.bookin.bookmanagement.usermanagement.dto.AuthRequest;
import com.bookin.bookmanagement.usermanagement.dto.AuthResponse;
import com.bookin.bookmanagement.usermanagement.dto.UserDto;
import com.bookin.bookmanagement.usermanagement.service.JwtService;
import com.bookin.bookmanagement.usermanagement.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@Slf4j
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;



    /**
     * Add new user response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<Object> addNewUser(@Validated @RequestBody UserDto userDto) {

        log.trace("UserController application addNewUser method invoked !");
        try {
            Object addUser = service.addUser(userDto);
            if (addUser != null)
                return APIResponseUtil.getResponseWithDataAndMessage(addUser, ResponseMessage.DATA_SAVED);

            return APIResponseUtil.getResponseForEmptyList();
        } catch (Exception e) {
            log.error("Error in UserController Method addNewUser {}",e.getMessage());
            return APIResponseUtil.getResponseWithMessage(e.getMessage());
        }
    }

    /**
     * Authenticate and get token object.
     *
     * @param authRequest the auth request
     * @return the object
     */
    @PostMapping("/login")
    public Object authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.trace("UserController application authenticateAndGetToken method invoked !");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return AuthResponse.builder().authToken(token).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}

