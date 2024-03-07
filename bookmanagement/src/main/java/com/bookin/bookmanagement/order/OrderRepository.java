package com.bookin.bookmanagement.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface OrderRepository extends JpaRepository<BookOrder, UUID> {
}
