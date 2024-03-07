package com.bookin.bookmanagement.usermanagement.dto;
import com.bookin.bookmanagement.address.AddressEntity;
import com.bookin.bookmanagement.constant.Country;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotNull(message = "username not to null")
    private String name;

    @Email
    @NotNull(message = "email not to be null")
    private String email;

    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    private Country country;

    @NotNull(message = "password not to be null")
    private String password;

    private String roles;

}
