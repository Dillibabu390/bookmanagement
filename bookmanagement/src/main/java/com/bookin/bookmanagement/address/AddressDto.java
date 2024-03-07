package com.bookin.bookmanagement.address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Integer houseNo;

    private String fullAddress;

    private Integer pinCode;

}
