package com.bookin.bookmanagement.book;

import com.bookin.bookmanagement.author.AuthorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BookDto {

    private UUID id;

    @NotNull(message = "book name not to be null")
    private String bookName;

    @NotNull(message = "book title not to be null")
    private String bookTitle;

    @NotNull(message = "description not to be null")
    private String description;

    @NotNull(message = "price not to be empty")
    private String price;

    @NotNull(message = "language not to be empty")
    private Language language;

    private AuthorDto author;

}
