package com.bookin.bookmanagement.cart;

import com.bookin.bookmanagement.book.BookEntity;
import com.bookin.bookmanagement.response.Info;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends Info{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @OneToMany
    @JoinColumn(name = "books_cart_id")
    private List<BookEntity> books;

}
