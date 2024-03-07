package com.bookin.bookmanagement.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotNull(message = "username not to be null")
    private String username;

    @NotNull(message = "password not to be null+")
    private String password;

}
