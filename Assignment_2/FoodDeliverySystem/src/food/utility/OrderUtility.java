package food.utility;

/**
 * Utility class for validating order data and generating summaries.
 */
public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(int orderId, String customerName, double amount) {
        return "Order #" + orderId + " | Customer: " + customerName + " | Amount: Rs. " + amount;
    }
}
