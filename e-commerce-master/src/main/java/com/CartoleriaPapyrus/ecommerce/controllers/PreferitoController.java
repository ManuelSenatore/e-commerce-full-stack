package com.CartoleriaPapyrus.ecommerce.controllers;

import com.CartoleriaPapyrus.ecommerce.entities.Preferito;
import com.CartoleriaPapyrus.ecommerce.entities.Prodotto;
import com.CartoleriaPapyrus.ecommerce.entities.User;
import com.CartoleriaPapyrus.ecommerce.services.PreferitoService;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.PreferitoRequest;
import com.CartoleriaPapyrus.ecommerce.utils.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferiti")
@Slf4j
@CrossOrigin(origins = "*")
public class PreferitoController {

    @Autowired
    PreferitoService preferitoService;

    // ADD PRODOTTO A PREFERITI
    @PostMapping("/aggiungi")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Preferito> addToPreferiti(@RequestBody PreferitoRequest preferitoRequest){
        try{
            return new ResponseEntity<>(preferitoService.aggiungiAiPreferiti(preferitoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error( e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // GET ALL FOR USER
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Prodotto>> getPreferiti(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(preferitoService.getPreferitiFromUser(id), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }

    // DELETE ELEMENT FROM PREFERITI
    @DeleteMapping("/delete/{elementoId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteElement(@PathVariable("userId") User userId, @PathVariable("elementoId") Integer elementoId) throws Exception {
        preferitoService.deleteElement(elementoId, userId);

        return new ResponseEntity<>(new ApiResponse( true, "Elemento eliminato"), HttpStatus.OK);
    }

    //DELETE ELEMENT FROM PRODOTTO ID
    @DeleteMapping("/delete/prodotto/{prodottoId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteElementFromProdotto(@PathVariable("userId") User userId, @PathVariable("prodottoId") Integer prodottoId) throws Exception {
        preferitoService.deleteElementByProdottoId(prodottoId, userId);

        return new ResponseEntity<>(new ApiResponse( true, "Elemento eliminato"), HttpStatus.OK);
    }
}
