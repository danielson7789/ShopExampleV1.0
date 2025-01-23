package org.example.shop.controllers;

import org.example.shop.Constants;
import org.example.shop.model.Billing;
import org.example.shop.model.CartItem;
import org.example.shop.model.Order;
import org.example.shop.services.CartService;
import org.example.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class OrderController {
    private final static Logger LOGGER = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderService orderService;

    @Autowired
    CartService cartService;

    @PostMapping(value="/place")
    public String placeOrder(Model viewModel,
                             @ModelAttribute Billing billing, RedirectAttributes atts) {

        List<CartItem> cartItems = cartService.getCart().getItems();
        LocalDateTime orderDateTime = LocalDateTime.now();
        Order order = new Order(cartItems,orderDateTime,billing);
        int orderId = orderService.addOrder(order);

        String message = String.format("Created order with ID %d", orderId);
        atts.addFlashAttribute(Constants.MESSAGE,message);
        LOGGER.info("message");

        return "redirect:/shop.html";
    }
}
