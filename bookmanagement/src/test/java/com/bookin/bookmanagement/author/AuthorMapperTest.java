package com.bookin.bookmanagement.author;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    @Test
    void authorToAuthorDto() {
        AuthorDto authorDto = AuthorDto.builder().authorName("author1").build();
       AuthorEntity  author = AuthorMapper.AUTHOR_MAPPER.AuthorDtoToAuthor(authorDto);
        assertEquals(author.getAuthorName(),authorDto.getAuthorName());

    }

    @Test
    void authorDtoToAuthor() {
        AuthorEntity author = AuthorEntity.builder().authorName("author1").build();
        AuthorDto authorDto = AuthorMapper.AUTHOR_MAPPER.AuthorToAuthorDto(author);
        assertEquals(author.getAuthorName(),authorDto.getAuthorName());
    }

    @Test
    void authorToAuthorsDto() {
        List<AuthorEntity> authorEntities = new ArrayList<>();
        authorEntities.add(AuthorEntity.builder().authorName("author1").build());
        authorEntities.add(AuthorEntity.builder().authorName("author2").build());

        List<AuthorDto> authorDtos = AuthorMapper.AUTHOR_MAPPER.AuthorToAuthorsDto(authorEntities);

        assertEquals(authorDtos.size(),authorEntities.size());
        assertEquals(authorDtos.get(0).getAuthorName(),authorEntities.get(0).getAuthorName());
        assertEquals(authorDtos.get(1).getAuthorName(),authorEntities.get(1).getAuthorName());
        
    }

    @Test
    void authorsToAuthorDto() {
        List<AuthorDto> authorDtos = new ArrayList<>();
        authorDtos.add(AuthorDto.builder().authorName("author1").build());
        authorDtos.add(AuthorDto.builder().authorName("author2").build());

        List<AuthorEntity> author = AuthorMapper.AUTHOR_MAPPER.AuthorsToAuthorDto(authorDtos);

        assertEquals(authorDtos.size(),author.size());
        assertEquals(authorDtos.get(0).getAuthorName(),author.get(0).getAuthorName());
        assertEquals(authorDtos.get(1).getAuthorName(),author.get(1).getAuthorName());


    }
}