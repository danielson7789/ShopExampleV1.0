package org.example.shop.enums;


/**
 * Represents a payment method
 *
 * @author Daniel Klenn
 * @version 1.7
 * @since 1.7
 */

public enum PaymentMethod {
    CREDITCARD   ("Credit card"),
    PAYPAL       ("Paypal"),
    BANK_TRANSFER("Direct bank transfer"),
    CHECK        ("Check payments");

    private final String label;

    PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

