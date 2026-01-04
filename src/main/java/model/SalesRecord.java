package model;

import java.time.LocalDate;

public class SalesRecord {
    private final int id;
    private final Customer customer;
    private final Employee seller;
    private final Tyre vehicle;
    private final LocalDate salesDate;
    private final double finalPrice;
    private final String paymentMethod;
    private final double cashAmount;
    private final double creditAmount;

    //constructor class
    public SalesRecord(int id, Customer customer, Employee seller, Tyre vehicle, LocalDate salesDate, double finalPrice, String paymentMethod, double cashAmount, double creditAmount) {
        this.id = id;
        this.customer = customer;
        this.seller = seller;
        this.vehicle = vehicle;
        this.salesDate = salesDate;
        this.finalPrice = finalPrice;
        this.paymentMethod = paymentMethod;
        this.cashAmount = cashAmount;
        this.creditAmount = creditAmount;
    }

    @Override
    public String toString() {
        return "Sales NO: " + id +
                " | Vehicle: " + vehicle.getDescription() +
                " | Customer: " + customer.getFullName() +
                " | Price: " + finalPrice + " TL";
    }

    //getter setters
    public int getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Employee getSeller() {
        return seller;
    }
    public Tyre getVehicle() {
        return vehicle;
    }
    public LocalDate getSalesDate() {
        return salesDate;
    }
    public double getFinalPrice() {
        return finalPrice;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public double getCashAmount() {
        return cashAmount;
    }
    public double getCreditAmount() {
        return creditAmount;
    }
}