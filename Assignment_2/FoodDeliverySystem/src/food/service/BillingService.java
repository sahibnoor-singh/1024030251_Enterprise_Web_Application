package food.service;

import food.model.Discountable;
import food.model.FoodOrder;

/**
 * Service class that handles bill computation and display for an order.
 */
public class BillingService {

    public void displayBill(FoodOrder order) {
        double discount = 0.0;
        if (order instanceof Discountable) {
            discount = ((Discountable) order).applyDiscount();
        }

        double deliveryCharge = order.calculateDeliveryCharge();
        double finalAmount = order.getAmount() - discount + deliveryCharge;

        System.out.println("----- Bill Summary -----");
        System.out.println("Order ID     : " + order.getOrderId());
        System.out.println("Customer     : " + order.getCustomerName());
        System.out.println("Restaurant   : " + FoodOrder.getRestaurantName());
        System.out.printf("Order Amount : Rs. %.2f%n", order.getAmount());
        System.out.printf("Discount     : Rs. %.2f%n", discount);
        System.out.printf("Delivery Chg : Rs. %.2f%n", deliveryCharge);
        System.out.printf("Final Payable: Rs. %.2f%n", finalAmount);
        System.out.println("-------------------------");
    }
}
