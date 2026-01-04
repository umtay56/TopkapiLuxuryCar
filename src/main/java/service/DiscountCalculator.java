package service;

import model.Manager;
import model.Person;

public class DiscountCalculator {

    public double calculateDiscountedPrice(Person seller, double originalPrice) {

        if (seller instanceof Manager) {
            Manager manager = (Manager) seller;
            int discountRate = manager.getAdminDiscount();
            double discountAmount = originalPrice * discountRate / 100;
            return originalPrice - discountAmount;
        }

        return originalPrice;
    }
}