package com.bookin.bookmanagement.usermanagement.dto;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AuthResponse {

    private String  authToken;

}
