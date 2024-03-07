package com.bookin.bookmanagement.order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "total_price")
    private String totalPrice;

  /*  @OneToOne
    @JoinColumn(name = "cart_id")
    Cart cart;


    @PostConstruct
    public void init(){
    List<BookEntity> books = cart.getBooks();
        quantity = books.size();
        int total =0;
        for (BookEntity book : books){
           total = total + book.getPrice();
        }
        totalPrice = total;

    }*/

}
