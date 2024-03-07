package com.bookin.bookmanagement.book;

import com.bookin.bookmanagement.author.AuthorDto;
import com.bookin.bookmanagement.author.AuthorEntity;
import com.bookin.bookmanagement.author.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
    }



    @Test
    void getBookById() {
        UUID existingId = UUID.randomUUID();
        BookEntity mockBookEntity = new BookEntity(existingId,"Book1","Title1",Language.MALAYALAM,
                new AuthorEntity(UUID.randomUUID(),"Author1"),"Description1","10");


        when(bookRepository.findById(existingId)).thenReturn(Optional.of(mockBookEntity));


        BookDto result = bookService.getBookById(existingId);

        assertEquals(mockBookEntity.getId(), result.getId());
        assertEquals(mockBookEntity.getBookName(), result.getBookName());
        assertEquals(mockBookEntity.getBookTitle(), result.getBookTitle());
        assertEquals(mockBookEntity.getDescription(), result.getDescription());
        assertEquals(mockBookEntity.getPrice(), result.getPrice());
        assertEquals(mockBookEntity.getLanguage(), result.getLanguage());
        assertEquals(mockBookEntity.getAuthor().getAuthorName(), result.getAuthor().getAuthorName());
    }

    @Test
    void getAllBooks() {


        List<BookEntity> mockBooks = new ArrayList<>();
        mockBooks.add(new BookEntity(UUID.randomUUID(),"Book1","Title1",Language.MALAYALAM,
                new AuthorEntity(UUID.randomUUID(),"Author1"),"Description1","10"));
        mockBooks.add(new BookEntity(UUID.randomUUID(),"Book2","Title2",Language.ENGLISH,
                new AuthorEntity(UUID.randomUUID(),"Author2"),"Description2","20"));

        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<BookDto> result = bookService.getAllBooks();

        assertEquals(mockBooks.size(), result.size());
        for (int i = 0; i < mockBooks.size(); i++) {
            BookDto expectedDto = BookMapper.INSTANCE.BookToBookDto(mockBooks.get(i));
            BookDto actualDto = result.get(i);
            assertEquals(expectedDto.getId(), actualDto.getId());
            assertEquals(expectedDto.getBookName(), actualDto.getBookName());
            assertEquals(expectedDto.getBookTitle(), actualDto.getBookTitle());
            assertEquals(expectedDto.getDescription(), actualDto.getDescription());
         //   assertEquals(expectedDto.getPrice(), actualDto.getPrice());
            assertEquals(expectedDto.getLanguage(), actualDto.getLanguage());
            assertEquals(expectedDto.getAuthor().getAuthorName(), actualDto.getAuthor().getAuthorName());
        }
    }


    @Test
    void saveBooks() {

        BookDto bookDto = BookDto.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorDto.builder().authorName("Author1").build())
                .build();

        BookEntity bookEntity = BookEntity.builder()
                .id(UUID.randomUUID())
                .bookName("Book1")
                .bookTitle("Title1")
                .description("Description1")
                .price(String.valueOf(10))
                .language(Language.ENGLISH)
                .author(AuthorEntity.builder().authorName("Author1").build())
                .build();

        BookDto responseBookDto = bookService.saveBooks(bookDto);

        assertEquals(bookDto.getBookTitle(), responseBookDto.getBookTitle());
        assertEquals(bookDto.getLanguage(), responseBookDto.getLanguage());
        assertEquals(bookDto.getDescription(), responseBookDto.getDescription());
        assertEquals(bookDto.getBookName(), responseBookDto.getBookName());
        assertEquals(bookDto.getPrice(), responseBookDto.getPrice());

    }

    @Test
    void updateBook() {


        UUID existingId = UUID.randomUUID();
        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setBookName("Updated Book Name");
        updatedBookDto.setBookTitle("Updated Title");
        updatedBookDto.setDescription("Updated Description");
        updatedBookDto.setPrice(String.valueOf(10));
        updatedBookDto.setAuthor(new AuthorDto("Updated Author Name"));

        BookEntity existingBookEntity = new BookEntity(existingId, "Book1", "Title1", Language.MALAYALAM,
                new AuthorEntity(UUID.randomUUID(), "Author1"), "Description1", "10");

        when(bookRepository.findById(existingId)).thenReturn(Optional.of(existingBookEntity));
        when(bookRepository.save(any())).thenReturn(existingBookEntity);
        when(authorRepository.save(any())).thenReturn(existingBookEntity.getAuthor());


        BookDto result = bookService.updateBook(existingId, updatedBookDto);


        assertEquals(updatedBookDto.getBookName(), result.getBookName());
        assertEquals(updatedBookDto.getBookTitle(), result.getBookTitle());
        assertEquals(updatedBookDto.getAuthor().getAuthorName(), result.getAuthor().getAuthorName());
    }

    @Test
    void deleteBookById() {
        UUID existingId = UUID.randomUUID();
        BookEntity existingBookEntity =  new BookEntity(existingId,"Book1","Title1",Language.MALAYALAM,
                new AuthorEntity(UUID.randomUUID(),"Author1"),"Description1","10");

        when(bookRepository.findById(existingId)).thenReturn(Optional.of(existingBookEntity));
        doNothing().when(bookRepository).deleteById(existingId);

        bookService.deleteBookById(existingId);

        verify(bookRepository, times(1)).deleteById(existingId);
    }
}