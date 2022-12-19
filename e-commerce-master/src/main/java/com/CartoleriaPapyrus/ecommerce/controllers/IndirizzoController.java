package com.CartoleriaPapyrus.ecommerce.controllers;

import com.CartoleriaPapyrus.ecommerce.entities.Indirizzo;
import com.CartoleriaPapyrus.ecommerce.repositories.IndirizzoRepository;
import com.CartoleriaPapyrus.ecommerce.services.IndirizzoService;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.IndirizzoRequest;
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
@RequestMapping("/api/indirizzi")
@Slf4j
@CrossOrigin(origins = "*")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    // GET ALL
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Indirizzo>> get() throws Exception {

        return new ResponseEntity<>(
                indirizzoRepository.findAll(),
                HttpStatus.OK
        );
    }

    // GET ALL PAGEABLE
    @GetMapping("/pageable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Indirizzo>> get( Pageable p ) throws Exception {

        return new ResponseEntity<>(
                indirizzoService.getAllPaginate( p ),
                HttpStatus.OK
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Indirizzo> get( @PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                indirizzoService.getById( id ),
                HttpStatus.OK
        );
    }

    // GET BY USER ID
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Indirizzo>> getIndirizziByUserId( @PathVariable("userId") Long userId ) throws Exception {
       try{
           return new ResponseEntity<>(
                   indirizzoService.getByUserId(userId),
                   HttpStatus.OK
           );
       }  catch( Exception e ) {

        log.error( e.getMessage() );

    }

        return new ResponseEntity<>( HttpStatus.OK );

    }


    // CREATE
    @PostMapping("/new-raw")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Indirizzo> create( @RequestBody IndirizzoRequest indirizzoRequest ) {

        try {


            return new ResponseEntity<>( indirizzoService.createAndSave( indirizzoRequest ), HttpStatus.OK );


        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return new ResponseEntity<>( HttpStatus.OK );

    }


    //UPDATE
    @PutMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Indirizzo> update( @RequestBody IndirizzoRequest indirizzoRequest ) {

        try {

            return new ResponseEntity<>( indirizzoService.createAndUpdate( indirizzoRequest ), HttpStatus.OK );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            indirizzoService.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }

}
