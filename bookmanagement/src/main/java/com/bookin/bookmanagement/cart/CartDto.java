package com.bookin.bookmanagement.cart;

import com.bookin.bookmanagement.book.BookDto;
import com.bookin.bookmanagement.response.Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto  {

    private UUID id;

    private List<BookDto> books;
}
