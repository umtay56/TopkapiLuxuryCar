package service;

public class PaymentValidator {

    public boolean isPaymentSufficient(double cashAmount, double creditAmount, double vehiclePrice) {
        double totalPayment = cashAmount + creditAmount;
        return Math.abs(totalPayment - vehiclePrice) < 0.01;
    }

    public String determinePaymentType(double cashAmount, double creditAmount) {
        if (cashAmount > 0 && creditAmount == 0) {
            return "Cash";
        } else if (cashAmount == 0 && creditAmount > 0) {
            return "Credit";
        } else {
            return "Cash + Credit";
        }
    }
}