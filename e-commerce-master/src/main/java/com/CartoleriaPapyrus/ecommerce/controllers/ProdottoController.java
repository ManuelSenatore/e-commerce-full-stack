package com.CartoleriaPapyrus.ecommerce.controllers;

import com.CartoleriaPapyrus.ecommerce.entities.Categoria;
import com.CartoleriaPapyrus.ecommerce.entities.Disponibilita;
import com.CartoleriaPapyrus.ecommerce.entities.Prodotto;
import com.CartoleriaPapyrus.ecommerce.services.ProdottoService;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.ProdottoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
@Slf4j
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    ProdottoService service;

    // GET ALL
    @GetMapping("")
    public ResponseEntity<List<Prodotto>> getAll(){
        try{
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // GET ALL AND PAGINATE
    @GetMapping("/pageable")
    public ResponseEntity<Page<Prodotto>> getAllPageable(Pageable p){
        try{
            return new ResponseEntity<>(service.getAllPaginate(p), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getById(@PathVariable("id") Long id) throws Exception {
        try{
            return new ResponseEntity<>(service.getById( id), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // RITORNA UNA LISTA DI PRODOTTI FILTRATI PER CATEGORIA
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoria( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategory( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/nomeDesc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByNameDesc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByNomeDesc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/nomeAsc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByNameAsc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByNomeAsc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/prezzoAsc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByPrezzoAsc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByPrezzoAsc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/prezzoDesc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByPrezzoDesc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByPrezzoDesc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    // CREATE
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prodotto> create(@RequestBody ProdottoRequest prodottoRequest){
        try{
            Prodotto prodotto = Prodotto.builder()
                    .nome(prodottoRequest.getNome())
                    .categoria(Categoria.valueOf( prodottoRequest.getCategoria()))
                    .descrizione(prodottoRequest.getDescrizione())
                    .disponibilita(Disponibilita.valueOf( prodottoRequest.getDisponibilita()))
                    .prezzo(prodottoRequest.getPrezzo())
                    .immagineUrl( prodottoRequest.getImmagineUrl())
                    .build();
            service.save(prodotto);
            return new ResponseEntity<>(prodotto, HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prodotto> update( @RequestBody ProdottoRequest prodottoRequest, @PathVariable("id") Long id ) {

        try {

            return service.update( prodottoRequest, id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            service.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
