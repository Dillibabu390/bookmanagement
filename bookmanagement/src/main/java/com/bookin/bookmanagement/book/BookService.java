package com.bookin.bookmanagement.book;
import com.bookin.bookmanagement.author.AuthorEntity;
import com.bookin.bookmanagement.author.AuthorRepository;
import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import com.bookin.bookmanagement.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final UserHelperService userHelperService;

    public BookDto getBookById(UUID id) {
        BookEntity book = bookRepository.findById(id).orElseThrow( () ->
                new BookNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND));
        BookDto bookDto = BookMapper.INSTANCE.BookToBookDto(book);
        bookDto.setPrice(CurrencyUtil.convertCurrency(book.getPrice(),userHelperService.getCurrentCountry()));
        return bookDto;
    }


    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtos = BookMapper.INSTANCE.booksToBooksDto(bookRepository.findAll());
        for (BookDto dto : bookDtos){
            dto.setPrice(CurrencyUtil.convertCurrency(dto.getPrice(),userHelperService.getCurrentCountry()));
        }
        return bookDtos;
    }


    public BookDto saveBooks(BookDto bookDto) {
        BookEntity bookEntity = BookMapper.INSTANCE.BookDtoToBook(bookDto);
        AuthorEntity author = bookEntity.getAuthor();
        bookEntity.setAuthor(authorRepository.save(author));
        bookRepository.save(bookEntity);
        return bookDto;
    }

    public BookDto updateBook(UUID id,BookDto bookDto) {

        Optional<BookEntity> books = bookRepository.findById(id);
        if (!books.isPresent()){
            throw new BookNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
        }
        BookEntity book = books.get();
       AuthorEntity author = book.getAuthor();
       author.setAuthorName(bookDto.getAuthor().getAuthorName());
       book.setAuthor(authorRepository.save(author));
       book.setBookTitle(bookDto.getBookTitle());
       book.setBookName(bookDto.getBookName());
       book.setPrice(book.getPrice());
       book.setDescription(book.getDescription());
       return BookMapper.INSTANCE.BookToBookDto(bookRepository.save(book));
    }


    public void deleteBookById(UUID id) {
        Optional<BookEntity> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
        }
        bookRepository.deleteById(id);
    }


}
