package org.example.shop.services;

import org.example.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public int addOrder(Order order) {
        // Add order to orders
        orders.add(order);
        // Return new orderId (assume it's the size of the list for simplicity)
        return order.getId();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        // Search orders by id and return the corresponding order
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        // Return null otherwise
        return null;
    }
}
