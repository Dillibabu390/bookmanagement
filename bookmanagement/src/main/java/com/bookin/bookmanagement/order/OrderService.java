package com.bookin.bookmanagement.order;
import com.bookin.bookmanagement.book.BookEntity;
import com.bookin.bookmanagement.cart.Cart;
import com.bookin.bookmanagement.cart.CartRepository;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import com.bookin.bookmanagement.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    private final UserHelperService userHelperService;

    public BookOrderDto bookOrders() {

        int total = 0;
        int qua =0;

        List<Cart> carts = cartRepository.findByCreatedBy(userHelperService.getCurrentUser());
        for (Cart cart: carts) {
            List<BookEntity> bookEntities = cart.getBooks();

            for (BookEntity book : bookEntities) {
                total = Integer.parseInt(total + book.getPrice());
                book.setPrice(CurrencyUtil.convertCurrency(book.getPrice(),userHelperService.getCurrentCountry()));
            }
            qua = qua+ bookEntities.size();
        }


        return BookOrderDto.builder()
                .date(Instant.now())
                .name(userHelperService.getCurrentUser())
                .totalPrice(CurrencyUtil.convertCurrency( String.valueOf(total),userHelperService.getCurrentCountry()))
                .quantity(qua)
                .cart(carts).build();

    }

    public BookOrder saveOrder() {

        double total = 0;
        int qua =0;
        List<Cart> carts = cartRepository.findByCreatedBy(userHelperService.getCurrentUser());
        for (Cart cart : carts) {
            List<BookEntity> bookEntities = cart.getBooks();


            for (BookEntity book : bookEntities) {
                total = total + Integer.parseInt(book.getPrice());
            }
            qua = qua+ bookEntities.size();
        }
        BookOrder bookOrder = BookOrder.builder()
                .name(userHelperService.getCurrentUser())
                .orderDate(Instant.now())
                .quantity(qua)
                .orderDate(Instant.now())
                .totalPrice(String.valueOf(total)).build();

      return orderRepository.save(bookOrder);
    }


    public List<BookOrder> getOrder() {
        return orderRepository.findAll();
    }
}
