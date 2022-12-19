package com.CartoleriaPapyrus.ecommerce.repositories;

import com.CartoleriaPapyrus.ecommerce.entities.Categoria;
import com.CartoleriaPapyrus.ecommerce.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria"
    )
    List<Prodotto> findProdottoByCategoria(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by nome asc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByNomeAsc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by nome desc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByNomeDesc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by prezzo desc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByPrezzoDesc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by prezzo asc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByPrezzoAsc(@Param("categoria") Categoria categoria);
}
