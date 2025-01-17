package org.example.shop.controllers;

import org.example.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    private final static Logger LOG = LogManager.getLogger(CartController.class);
    protected static final String MESSAGE = "message";

    @Autowired
    CartService cartService;

    @GetMapping(value = "/add/{productId}")
    public String addToCart(
            @PathVariable(name = "productId") Integer productId, RedirectAttributes atts) {
        String productName = cartService.addProduct(productId);
        if (productName != null) {
            String message = String.format("'%s' added to the cart", productName);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.info(message);
        } else {
            String message = String.format("Product with ID '%s' could not be found", productId);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.warn(message);
        }
        return "redirect:/index.html";
    }

    @GetMapping({"/increase/{productId}"})
    public String increaseQuantity(@PathVariable(name="productId") Integer productId){
        boolean isSuccessful = cartService.increaseQuantity(productId);
        if(isSuccessful){
            LOG.info("Quantity of cartItem with Id '{}' increased" ,productId);
        }else{
            LOG.warn("Product with ID '{}' could not be found", productId);
        }
        return "redirect:/cart.html";
    }

    @GetMapping({"/decrease/{productId}"})
    public String decreaseQuantity(@PathVariable(name="productId") Integer productId){
        boolean isSuccessful = cartService.decreaseQuantity(productId);
        if(isSuccessful){
            LOG.info("Quantity of cartItem with Id '{}' decreased" ,productId);
        }else{
            LOG.warn("Product with ID '{}' could not be found", productId);
        }
        return "redirect:/cart.html";
    }

    @GetMapping({"/remove/{productId}"})
    public String removeFromCart(@PathVariable(name="productId") Integer productId , RedirectAttributes atts){
        String shortName = cartService.removeProduct(productId);
        if (shortName != null) {
            String message = String.format("'%s' removed from the cart", shortName);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.info(message);
        }else{
            String message = String.format("Product with ID '%s' could not be found in cart", productId);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.warn(message);
        }
        return "redirect:/cart.html";
    }
}
