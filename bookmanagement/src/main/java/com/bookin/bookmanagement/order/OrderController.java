package com.bookin.bookmanagement.order;

import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.response.APIResponseUtil;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    private final UserHelperService userHelperService;

    private final SendEmailService sendEmailService;


    @GetMapping("/get")
   @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getAllOrder(){
        log.trace("getAllOrder collaboration get all method invoked OrderController !");
        try {
            BookOrderDto books = orderService.bookOrders();
            if (books == null) {
                return APIResponseUtil.getResponseForEmptyList();
            }
            return APIResponseUtil.getResponseWithData(books);
        }catch (Exception e){
            log.error("Error in OrderController Method getAllOrder :",e);
            return  APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }
    }




    @PostMapping("/submit")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> saveOrder(){
        log.trace("collaboration OrderController details application save method invoked !");
        try {
            BookOrder bookOrder = orderService.saveOrder();
            if (bookOrder != null)
                return APIResponseUtil.getResponseWithDataAndMessage(bookOrder, ResponseMessage.DATA_SAVED);

            sendEmailService.sendSimpleEmail(userHelperService.getCurrentEmail(),
                        "ORDER BOOKS","hi the total book ordered<br>"+ bookOrder.getQuantity()+"<br>"+"Total price:"+
                        "<b>"+ bookOrder.getTotalPrice());

            return APIResponseUtil.getResponseForEmptyList();
        } catch (Exception e) {
            log.error("Error in OrderController Method saveOrder :",e);
            return APIResponseUtil.getResponseWithMessage(e.getMessage());
        }
    }

    @GetMapping("/getOders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> getOrders(){
        log.trace("getOrders collaboration get all method invoked OrderController !");
        try {
            List<BookOrder> booksOrder = orderService.getOrder();
            if (booksOrder.isEmpty()) {
                return APIResponseUtil.getResponseForEmptyList();
            }
            return APIResponseUtil.getResponseWithData(booksOrder);
        }catch (Exception e){
            log.error("Error in OrderController Method getOrders :",e);
            return  APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }

    }



}
