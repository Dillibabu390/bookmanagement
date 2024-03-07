package com.bookin.bookmanagement.book;

import com.bookin.bookmanagement.author.AuthorDto;
import com.bookin.bookmanagement.author.AuthorEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {


    @Test
    void bookToBookDto() {
         BookDto bookDto = BookDto.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                 .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorDto.builder().authorName("Author1").build())
                .build();

       BookEntity bookEntity = BookMapper.INSTANCE.BookDtoToBook(bookDto);

        assertEquals(bookDto.getBookTitle(),bookEntity.getBookTitle());
        assertEquals(bookDto.getBookName(),bookEntity.getBookName());
        assertEquals(bookDto.getAuthor().getAuthorName(),bookEntity.getAuthor().getAuthorName());
        assertEquals(bookDto.getDescription(),bookEntity.getDescription());
        assertEquals(bookDto.getLanguage(),bookEntity.getLanguage());
        assertEquals(bookDto.getPrice(),bookEntity.getPrice());
    }

    @Test
    void bookDtoToBook() {
        BookEntity book = BookEntity.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorEntity.builder().authorName("Author1").build())
                .build();

        BookDto bookDto = BookMapper.INSTANCE.BookToBookDto(book);

        assertEquals(bookDto.getBookTitle(),book.getBookTitle());
        assertEquals(bookDto.getBookName(),book.getBookName());
        assertEquals(bookDto.getAuthor().getAuthorName(),book.getAuthor().getAuthorName());
        assertEquals(bookDto.getDescription(),book.getDescription());
        assertEquals(bookDto.getLanguage(),book.getLanguage());
        assertEquals(bookDto.getPrice(),book.getPrice());

        
    }

    @Test
    void booksToBooksDto() {
        List<BookDto> bookDto = new ArrayList<>();

                 bookDto.add(BookDto.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorDto.builder().authorName("Author1").build())
                .build());
        bookDto.add(BookDto.builder()
                .id(UUID.randomUUID())
                .bookName("Book2")
                .bookTitle("Title2")
                .description("Description2")
                .price(String.valueOf(11))
                .language(Language.ENGLISH)
                .author(AuthorDto.builder().authorName("Author2").build())
                .build());

    List<BookEntity>  books = BookMapper.INSTANCE.booksDtoToBooks(bookDto);
        assertEquals(bookDto.size(),books.size());
        assertEquals(bookDto.get(0).getBookName(),books.get(0).getBookName());
        assertEquals(bookDto.get(0).getBookTitle(),books.get(0).getBookTitle());
        assertEquals(bookDto.get(0).getDescription(),books.get(0).getDescription());
        assertEquals(bookDto.get(0).getLanguage(),books.get(0).getLanguage());
        assertEquals(bookDto.get(0).getAuthor().getAuthorName(),books.get(0).getAuthor().getAuthorName());

        assertEquals(bookDto.get(1).getBookName(),books.get(1).getBookName());
        assertEquals(bookDto.get(1).getBookTitle(),books.get(1).getBookTitle());
        assertEquals(bookDto.get(1).getDescription(),books.get(1).getDescription());
        assertEquals(bookDto.get(1).getLanguage(),books.get(1).getLanguage());
        assertEquals(bookDto.get(1).getAuthor().getAuthorName(),books.get(1).getAuthor().getAuthorName());


    }

    @Test
    void booksDtoToBooks() {
        List<BookEntity> bookEntities = new ArrayList<>();

        bookEntities.add(BookEntity.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorEntity.builder().authorName("Author1").build())
                .build());
        bookEntities.add(BookEntity.builder()
                .id(UUID.randomUUID())
                .bookName("Book2")
                .bookTitle("Title2")
                .description("Description2")
                .price(String.valueOf(11))
                .language(Language.ENGLISH)
                .author(AuthorEntity.builder().authorName("Author2").build())
                .build());

        List<BookDto>  books = BookMapper.INSTANCE.booksToBooksDto(bookEntities);
        assertEquals(bookEntities.size(),books.size());
        assertEquals(bookEntities.get(0).getBookName(),books.get(0).getBookName());
        assertEquals(bookEntities.get(0).getBookTitle(),books.get(0).getBookTitle());
        assertEquals(bookEntities.get(0).getDescription(),books.get(0).getDescription());
        assertEquals(bookEntities.get(0).getLanguage(),books.get(0).getLanguage());
        assertEquals(bookEntities.get(0).getAuthor().getAuthorName(),books.get(0).getAuthor().getAuthorName());

        assertEquals(bookEntities.get(1).getBookName(),books.get(1).getBookName());
        assertEquals(bookEntities.get(1).getBookTitle(),books.get(1).getBookTitle());
        assertEquals(bookEntities.get(1).getDescription(),books.get(1).getDescription());
        assertEquals(bookEntities.get(1).getLanguage(),books.get(1).getLanguage());
        assertEquals(bookEntities.get(1).getAuthor().getAuthorName(),books.get(1).getAuthor().getAuthorName());

    }
}