package com.CartoleriaPapyrus.ecommerce.controllers;

import com.CartoleriaPapyrus.ecommerce.entities.Carrello;
import com.CartoleriaPapyrus.ecommerce.entities.User;
import com.CartoleriaPapyrus.ecommerce.services.CarrelloService;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.AggiungiAlCarrelloRequest;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.CarrelloRequest;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.UpdateQuantityRequest;
import com.CartoleriaPapyrus.ecommerce.utils.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrello")
@Slf4j
@CrossOrigin(origins = "*")
public class CarrelloController {

    @Autowired
    CarrelloService carrelloService;


    // AGGIUNGI PRODOTTO AL CARRELLO
    @PostMapping("/aggiungi")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Carrello> aggiungiAlCarrello(@RequestBody AggiungiAlCarrelloRequest aggiungiAlCarrelloRequest) throws Exception {
        try{
            return new ResponseEntity<>(carrelloService.aggiungiAlCarrello(aggiungiAlCarrelloRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }

    // GET CARRELLO FOR A USER
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CarrelloRequest> getElementoDelCarrello(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(carrelloService.getAllElements(id), HttpStatus.OK);
        } catch( Exception e ) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }

    // DELETE ELEMENTO DEL CARRELLO BY USER
    @DeleteMapping("/delete/{elementoId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteElement(@PathVariable("userId") User userId, @PathVariable("elementoId") Integer elementoId) throws Exception {

        carrelloService.deleteElement(elementoId, userId);

        return new ResponseEntity<>(new ApiResponse(true, "Elemento eliminato"), HttpStatus.OK);
    }

     // PUT QUANTITY
    @PutMapping("update/{elementoId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Carrello> update(@RequestBody UpdateQuantityRequest carrelloRequest, @PathVariable("elementoId") Long elementoId) throws Exception {
        try {
            return new ResponseEntity<>(carrelloService.update(carrelloRequest, elementoId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
