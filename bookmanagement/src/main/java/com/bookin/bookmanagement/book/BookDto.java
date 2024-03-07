package com.bookin.bookmanagement.book;
import com.bookin.bookmanagement.author.AuthorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private String bookName;

    private String bookTitle;

    private String description;

    private String price;

    private Language language;

    private AuthorDto author;

}
