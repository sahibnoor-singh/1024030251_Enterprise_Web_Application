package food.model;

public class RegularOrder extends FoodOrder implements Discountable {

    private static double DELIVERY_CHARGE = 80.0;
    private static double DISCOUNT_PERCENT = 10.0; // flat 10 % 

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return DELIVERY_CHARGE;
    }

    @Override
    public double applyDiscount() {
        return (getAmount() * DISCOUNT_PERCENT) / 100.0;
    }
}
