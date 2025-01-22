package org.example.shop.model;

import org.example.shop.enums.PaymentMethod;


public class Billing {
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;
    private String email;
    private PaymentMethod paymentMethod;

    // Full-args constructor
    public Billing(String firstName, String lastName, String address, String zipCode, String city,
                   String phoneNumber, String email, PaymentMethod paymentMethod) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}

