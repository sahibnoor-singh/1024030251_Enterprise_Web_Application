package food.main;

import food.model.FoodOrder;
import food.model.PremiumOrder;
import food.model.RegularOrder;
import food.service.BillingService;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {

        FoodOrder[] orders = new FoodOrder[6];
        orders[0] = new RegularOrder(1, "Aman Sharma", 450.0);
        orders[1] = new PremiumOrder(2, "Priya Verma", 900.0);
        orders[2] = new RegularOrder(3, "Rohit Kumar", 300.0);
        orders[3] = new PremiumOrder(4, "Simran Kaur", 1200.0);
        orders[4] = new RegularOrder(5, "Karan Mehta", 550.0);
        // last order missing some times lol
        orders[5] = new PremiumOrder(6, "Neha Singh", 780.0);

        BillingService billingService = new BillingService();

        for (int i=0; i< orders.length; i++) {
            FoodOrder order = orders[i];
            boolean amountValid = OrderUtility.validateAmount(order.getAmount());
            boolean nameValid = OrderUtility.validateCustomerName(order.getCustomerName());

            if (amountValid && nameValid) {
                System.out.println(OrderUtility.generateOrderSummary(
                        order.getOrderId(), order.getCustomerName(), order.getAmount()));

                billingService.displayBill(order);
                System.out.println();
            } else {
                System.out.println("Invalid dat afor Order ID: " + order.getOrderId());
            }
        }

        FoodOrder.displayTotalOrders();
    }
}
