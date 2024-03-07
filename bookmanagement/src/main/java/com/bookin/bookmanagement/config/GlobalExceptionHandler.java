package com.bookin.bookmanagement.config;
import com.bookin.bookmanagement.response.APIResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.NoSuchElementException;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Message not readable response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> messageNotReadable(HttpMessageNotReadableException ex) {
        return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }

    /**
     * Method argument type mismatch response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }


    /**
     * No such element response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElement(NoSuchElementException ex) {
       return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }

    /**
     * Handle invalid argument response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
        return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }

    /**
     * Expired jwt response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> expiredJwt(ExpiredJwtException ex) {
        return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }

    /**
     * User not found response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UsernameNotFoundException ex) {
        return APIResponseUtil.getResponseWithErrorMessage(ex.toString());
    }

}
