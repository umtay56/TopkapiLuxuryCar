package service;

import model.SalesRecord;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReceiptBuilder {

    public String generateReceipt(SalesRecord record) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
        String formattedDateTime = now.format(dtf);


        String formattedPrice = String.format(new Locale("tr", "TR"), "%,.0f", record.getFinalPrice());

        StringBuilder receipt = new StringBuilder();

        receipt.append("========================================\n");
        receipt.append("       TOPKAPI LUXURY CAR SALES         \n");
        receipt.append("          Istanbul, Turkiye             \n");
        receipt.append("========================================\n");
        receipt.append("DATE/TIME  : ").append(formattedDateTime).append("\n");
        receipt.append("RECEIPT NO : #").append(String.format("%06d", record.getId())).append("\n");
        receipt.append("SALES REP  : ").append(record.getSeller().getFullName().toUpperCase()).append("\n");
        receipt.append("----------------------------------------\n");
        receipt.append("CUSTOMER INFORMATION:\n");
        receipt.append("NAME       : ").append(record.getCustomer().getFullName().toUpperCase()).append("\n");
        receipt.append("----------------------------------------\n");
        receipt.append("VEHICLE DETAILS:\n");
        receipt.append("MODEL      : ").append(record.getVehicle().getBrand()).append(" ").append(record.getVehicle().getModel()).append("\n");
        receipt.append("PLATE      : ").append(record.getVehicle().getPlate()).append("\n");
        receipt.append("----------------------------------------\n");
        receipt.append("PAYMENT METHOD : ").append(record.getPaymentMethod()).append("\n");
        receipt.append("TOTAL AMOUNT   : ").append(formattedPrice).append(" TL\n");
        receipt.append("----------------------------------------\n");
        receipt.append("      Thank you for choosing us!        \n");
        receipt.append("     Your journey starts with luxury.   \n");
        receipt.append("========================================\n");

        return receipt.toString();
    }
}