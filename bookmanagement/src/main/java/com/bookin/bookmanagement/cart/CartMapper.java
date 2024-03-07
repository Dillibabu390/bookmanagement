package com.bookin.bookmanagement.cart;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {

    CartMapper CART_INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto cartToCartDto(Cart cart);

    Cart cartDtoToCart(CartDto cartDto);

    List<CartDto> cartsToCartDto(List<Cart> carts);

    List<Cart> cartsDtoToCarts(List<CartDto> cartDtos);
}
