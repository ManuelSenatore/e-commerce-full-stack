package com.CartoleriaPapyrus.ecommerce.controllers;

import com.CartoleriaPapyrus.ecommerce.entities.User;
import com.CartoleriaPapyrus.ecommerce.services.OrderService;
import com.CartoleriaPapyrus.ecommerce.utils.RequestModels.CheckoutRequest;
import com.CartoleriaPapyrus.ecommerce.utils.ResponseModels.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

     // STRIPE CHECKOUT API
    @PostMapping("/create-checkout-session")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutRequest> checkoutRequestList) throws StripeException {
        Session session = orderService.createSession(checkoutRequestList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
