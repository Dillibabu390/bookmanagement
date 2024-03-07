package com.bookin.bookmanagement.cart;

import com.bookin.bookmanagement.author.AuthorRepository;
import com.bookin.bookmanagement.book.*;
import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import com.bookin.bookmanagement.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final UserHelperService userHelperService;

    private final AuthorRepository authorRepository;

    public List<Cart> getAllCartItem() {
        List<Cart> carts = cartRepository.findAll();
        for (Cart cart : carts){
           List<BookEntity> books = cart.getBooks();
           for (BookEntity book : books){
               book.setPrice(CurrencyUtil.convertCurrency(book.getPrice(),userHelperService.getCurrentCountry()));
           }
        }
        return carts;
    }

    public Cart saveAllCartItem(CartDto cartDto) {

        List<BookDto> books = cartDto.getBooks();

       List<BookEntity> bookEntities = new ArrayList<>();
        for (BookDto book:books){
            Optional<BookEntity> bookEntity = bookRepository.findById(book.getId());
            if (!bookEntity.isPresent()){
                throw new BookNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
            }
            BookEntity book1 = bookEntity.get();

           bookEntities.add(book1);
        }
        Cart cart = new Cart();
        cart.setBooks(bookEntities);
        return cartRepository.save(cart);
    }


    public CartDto findBookByCreatedBy(String currentUser) {
        CartDto cartDto = new CartDto();

        List<Cart> carts = cartRepository.findByCreatedBy(currentUser);

        List<BookEntity> books = new ArrayList<>();
        for (Cart cart:carts){
            cartDto.setId(cart.getId());
          List<BookEntity> bookEntities = cart.getBooks();
          for (BookEntity book: bookEntities){
              book.setPrice(CurrencyUtil.convertCurrency(book.getPrice(),userHelperService.getCurrentCountry()));
              books.add(book);
          }
        }
        cartDto.setBooks(BookMapper.INSTANCE.booksToBooksDto(books));

        if (carts.isEmpty()) {
            throw new CartItemNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
        }
        return cartDto;
    }

    public void deleteCartById(UUID id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (!cartOptional.isPresent()) {
            throw new CartItemNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
        }
        cartRepository.deleteById(id);
    }

    public Cart saveItemsById(UUID id) {
        List<BookEntity> books = new ArrayList<>();
        Optional<BookEntity> book = bookRepository.findById(id);
        if (!book.isPresent()){
            throw new BookNotFoundException(ResponseMessage.AUD_NO_RECORDS_FOUND);
        }
        BookEntity booke = book.get();
        Cart cart = new Cart();
        books.add(booke);
        cart.setBooks(books);
       return cartRepository.save(cart);
    }


}
