package com.bookin.bookmanagement.book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto BookToBookDto(BookEntity bookEntity);
    BookEntity BookDtoToBook(BookDto bookDto);

    List<BookDto> booksToBooksDto(List<BookEntity> books);

    List<BookEntity> booksDtoToBooks(List<BookDto> bookDto);



}
