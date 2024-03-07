package com.bookin.bookmanagement.order;

import com.bookin.bookmanagement.cart.Cart;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookOrderDto {

    private String name;

    private Integer quantity;

    private Instant date;

    private String totalPrice;

    List<Cart> cart;
}
