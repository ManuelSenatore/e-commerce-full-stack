package com.CartoleriaPapyrus.ecommerce.repositories;


import com.CartoleriaPapyrus.ecommerce.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

    @Query(
            value = "select i from Indirizzo i where i.user.id = :id"
    )
    List<Indirizzo> findByUser(@PathVariable("id") Long id);
}
