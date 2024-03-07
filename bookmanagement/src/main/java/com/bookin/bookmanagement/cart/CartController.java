package com.bookin.bookmanagement.cart;

import com.bookin.bookmanagement.book.BookNotFoundException;
import com.bookin.bookmanagement.constant.ResponseMessage;
import com.bookin.bookmanagement.response.APIResponseUtil;
import com.bookin.bookmanagement.usermanagement.service.UserHelperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Cart controller.
 */
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    private final UserHelperService userHelperService;


    /**
     * Get all cart items response entity.
     *
     * @return the response entity
     */
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllCartItems(){

        log.trace("getAllCartItems collaboration get all method invoked CartController !");
        try {
            List<Cart> carts = cartService.getAllCartItem();
            if (carts.isEmpty()) {
                return APIResponseUtil.getResponseForEmptyList();
            }
            return APIResponseUtil.getResponseWithData(carts);
        }catch (Exception e){
            log.error("Error in CartController Method getAllCartItems :",e);
            return  APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }
    }

    /**
     * Save cart items response entity.
     *
     * @param cartDto the cart dto
     * @return the response entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> saveCartItems(@RequestBody CartDto cartDto){
        log.trace("collaboration CartController details saveCartItems method invoked !");
        try {
             Cart cart = cartService.saveAllCartItem(cartDto);
            if (cart != null)
                return APIResponseUtil.getResponseWithDataAndMessage(cart, ResponseMessage.DATA_SAVED);

            return APIResponseUtil.getResponseForEmptyList();
        }catch (BookNotFoundException e){
            log.error("Unable to fetch UUID in CartController Method getBookById  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        }
        catch (Exception e) {
            log.error("Error in CartController Method saveCartItems :",e);
            return APIResponseUtil.getResponseWithMessage(e.getMessage());
        }

    }

    /**
     * Save cart item by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PostMapping("/save/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> saveCartItemById(@PathVariable UUID id){
        try {
            if (id == null) {
                return APIResponseUtil.getResponseWithErrorMessage(ResponseMessage.APP_INVALID_APPLICATION_UUID);
            }
            Cart cart = cartService.saveItemsById(id);
            return APIResponseUtil.getResponseWithData(cart);
        }catch (BookNotFoundException e){
            log.error("Unable to fetch UUID in CartController Method getBookById  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        }
        catch (Exception e) {
            log.error("Error in CartController Method saveCartItemById :",e);
            return APIResponseUtil.getResponseWithDataAndErrorMessage(e.getMessage(),
                    ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Get based items response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getCart")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getBasedItems(){
        log.trace("getBasedItems collaboration get all method in CartController invoked !");
        try {
            CartDto carts = cartService.findBookByCreatedBy(userHelperService.getCurrentUser());
            if (carts == null) {
                return APIResponseUtil.getResponseForEmptyList();
            }
            return APIResponseUtil.getResponseWithData(carts);

        }catch (CartItemNotFoundException e){
            log.error("Error in CartController Method getBasedItems :",e);
            return  APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }
        catch (Exception e){
            log.error("Error in CartController Method getBasedItems :",e);
            return  APIResponseUtil.getResponseWithErrorMessageAndErrorCode(e.getMessage());
        }
    }


    /**
     * Delete cart item by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> deleteCartItemById(@PathVariable UUID id){

        log.trace("delete CartController collaboration details by deleteCartItem UUID method invoked !");
        try {
            if (id == null) {
                return APIResponseUtil.getResponseWithErrorMessage(ResponseMessage.APP_INVALID_APPLICATION_UUID);
            }
            cartService.deleteCartById(id);

            return APIResponseUtil.getResponseWithMessage(ResponseMessage.DELETED);
        } catch (CartItemNotFoundException e) {
            log.error("Unable to fetch UUID in CartController Method deleteCartItemById  : ", e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Error in CartController Method deleteCartItemById :",e);
            return APIResponseUtil.getResponseWithErrorMessage(e.getMessage());
        }
    }


}
