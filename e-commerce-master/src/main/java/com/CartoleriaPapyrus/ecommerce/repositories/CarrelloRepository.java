package com.CartoleriaPapyrus.ecommerce.repositories;

import com.CartoleriaPapyrus.ecommerce.entities.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

    @Query(
            value = "select c from Carrello c where c.user.id = :id order by c.createdDate desc"
    )
    List<Carrello> findbyUserOrderByCreatedDateDesc(@Param("id") Long id);
}
