package com.CartoleriaPapyrus.ecommerce.repositories;

import com.CartoleriaPapyrus.ecommerce.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {

    @Query(
            value = "select c from Comune c where c.nome = :nome"
    )
    Optional<Comune> findByNome(@PathVariable("nome") String nome);
}
