package org.example.shop.model;

import org.example.shop.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 * This class represents a Order
 *
 * @author Daniel Klenn
 * @version 1.7
 * @since 1.0
 */
public class Order {
    private static int orderCounter = 1;
    private int id;
    private List<CartItem> items;
    private LocalDateTime orderDate;
    private LocalDate deliveryDate;
    private Billing billing;
    private double subTotal;
    private double discount;
    private double shipping;
    private double includedTax;
    private double grandTotal;

    public Order( List<CartItem> items, LocalDateTime orderDate, Billing billing) {
        this.id = orderCounter++;
        this.items = items;
        this.orderDate = orderDate;
        this.billing = billing;
    }

    public Order(List<CartItem> items){
        this.items = items;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public void setIncludedTax(double includedTax) {
        this.includedTax = includedTax;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Billing getBilling() {
        return billing;
    }

    public String getSubTotal() {
        double totals = 0;
        for (CartItem item : items) {
            totals += item.getTotalPrice();
        }
        subTotal = totals;
        return trimDecimals(subTotal);
    }

    public String getShipping() {
        shipping = Constants.SHIPPING_COSTS;
        return trimDecimals(shipping);
    }

    public String getIncludedTax() {
        includedTax = subTotal * Constants.VAT_FACTOR;
        return trimDecimals(includedTax);
    }

    public String getGrandTotal() {
        grandTotal = subTotal + shipping + includedTax - discount;
        return trimDecimals(grandTotal);
    }

    public String getDiscount() {
        discount = Constants.DISCOUNT_FACTOR * subTotal;
        return trimDecimals(discount);
    }

    private String trimDecimals(double number){
        return String.format(Locale.US,"%.2f", number);
    }
}