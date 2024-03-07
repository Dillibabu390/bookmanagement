package com.bookin.bookmanagement.book;

import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.response.APIResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * The type Book controller.
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;


    /**
     * Gets books.
     *
     * @return the books
     */
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getBooks() {
        log.trace("getBooks collaboration get all method invoked BookController !");
        try {
            List<BookDto> books = bookService.getAllBooks();
            if (books.isEmpty()) {
                return APIResponseUtil.getResponseForEmptyList();
            }
            return APIResponseUtil.getResponseWithData(books);
        } catch (Exception e) {
            log.error("Error in BookController Method getBooks :", e);
            return APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }
    }


    /**
     * Save books response entity.
     *
     * @param bookDto the book dto
     * @return the response entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> saveBooks(@Valid @RequestBody BookDto bookDto) {
        log.trace("collaboration BookController details application save method invoked !");
        try {
            BookDto savedBaunint = bookService.saveBooks(bookDto);
            if (savedBaunint != null)
                return APIResponseUtil.getResponseWithDataAndMessage(savedBaunint, ResponseMessage.DATA_SAVED);

            return APIResponseUtil.getResponseForEmptyList();
        } catch (Exception e) {
            log.error("Error in BookController Method saveBooks :", e);
            return APIResponseUtil.getResponseWithMessage(e.getMessage());
        }
    }

    /**
     * Gets book by id.
     *
     * @param id the id
     * @return the book by id
     */
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getBookById(@PathVariable UUID id) {

        log.trace("Get collaboration details by application UUID method invoked !");
        try {
            if (id == null) {
                return APIResponseUtil.getResponseWithErrorMessage(ResponseMessage.APP_INVALID_APPLICATION_UUID);
            }
            BookDto book = bookService.getBookById(id);
            return APIResponseUtil.getResponseWithData(book);
        } catch (BookNotFoundException e) {
            log.error("Unable to fetch UUID in BookController Method getBookById  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Error in BookController Method getBookById :", e);
            return APIResponseUtil.getResponseWithDataAndErrorMessage(e.getMessage(),
                    ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update book response entity.
     *
     * @param id      the id
     * @param bookDto the book dto
     * @return the response entity
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> updateBook(@PathVariable UUID id,@Valid @RequestBody BookDto bookDto) {

        log.trace("update collaboration BookController details by application UUID method invoked !");
        try {
            if (id == null) {
                return APIResponseUtil.getResponseWithErrorMessage(ResponseMessage.APP_INVALID_APPLICATION_UUID);
            }
            if (bookDto != null)
                return APIResponseUtil.getResponseWithData(bookService.updateBook(id, bookDto));

            return APIResponseUtil.getResponseForEmptyList();
        } catch (BookNotFoundException e) {
            log.error("Unable to fetch UUID in BookController Method updateBook  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Error in BookController Method updateBook : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        }
    }

    /**
     * Delete book by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteBookById(@PathVariable UUID id) {
        log.trace("delete collaboration BookController details by application UUID method invoked !");
        try {
            if (id == null) {
                return APIResponseUtil.getResponseWithErrorMessage(ResponseMessage.APP_INVALID_APPLICATION_UUID);
            }
            bookService.deleteBookById(id);

            return APIResponseUtil.getResponseWithMessage(ResponseMessage.DELETED);
        } catch (BookNotFoundException e) {
            log.error("Unable to fetch UUID in BookController Method deleteBookById  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Error in BookController Method deleteBookById :", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        }

    }


}
