package com.bookin.bookmanagement.author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;



@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
}
