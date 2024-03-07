package com.bookin.bookmanagement.author;

import com.bookin.bookmanagement.book.BookDto;
import com.bookin.bookmanagement.book.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);

    AuthorDto AuthorToAuthorDto(AuthorEntity authorEntity);

    AuthorEntity AuthorDtoToAuthor(AuthorDto authorDto);

    List<AuthorDto> AuthorToAuthorsDto(List<AuthorEntity> authorEntity);

    List<AuthorEntity> AuthorsToAuthorDto(List<AuthorDto> authorDto);



}
