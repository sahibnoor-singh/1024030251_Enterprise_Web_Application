// Q7 - Shopping cart application with a complete exception hierarchy
import java.util.HashMap;
import java.util.ArrayList;

// Base exception
class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

// Product exceptions
class ProductException extends ApplicationException {
    public ProductException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductException {
    public OutOfStockException(String message) {
        super(message);
    }
}

// Payment exceptions
class PaymentException extends ApplicationException {
    public PaymentException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends PaymentException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Order exceptions
class OrderException extends ApplicationException {
    public OrderException(String message) {
        super(message);
    }
}

class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}

public class Q7_ShoppingCart {

    static HashMap<String, Integer> stock = new HashMap<>();       // product -> quantity in stock
    static HashMap<String, Double> prices = new HashMap<>();       // product -> price
    static ArrayList<String> cart = new ArrayList<>();

    static void addToCart(String product) throws ProductNotFoundException, OutOfStockException {
        if (!stock.containsKey(product)) {
            throw new ProductNotFoundException("Product not found: " + product);
        }
        if (stock.get(product) <= 0) {
            throw new OutOfStockException("Product out of stock: " + product);
        }
        cart.add(product);
        stock.put(product, stock.get(product) - 1);
        System.out.println(product + " added to cart");
    }

    static void removeFromCart(String product) {
        cart.remove(product);
        stock.put(product, stock.get(product) + 1);
        System.out.println(product + " removed from cart");
    }

    static double calculateTotal() throws EmptyCartException {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cannot checkout, cart is empty");
        }
        double total = 0;
        for (String item : cart) {
            total += prices.get(item);
        }
        return total;
    }

    static void makePayment(double amount, double balance) throws InvalidPaymentException,
            InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidPaymentException("Payment amount must be greater than zero");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds to complete payment");
        }
        System.out.println("Payment of " + amount + " successful");
    }

    public static void main(String[] args) {
        // setup some products
        stock.put("Laptop", 2);
        stock.put("Mouse", 0); // out of stock on purpose
        prices.put("Laptop", 50000.0);
        prices.put("Mouse", 500.0);

        double walletBalance = 40000.0;

        try {
            addToCart("Laptop");
            addToCart("Mouse"); // this will throw OutOfStockException
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            addToCart("Keyboard"); // this will throw ProductNotFoundException
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            double total = calculateTotal();
            System.out.println("Cart total: " + total);
            makePayment(total, walletBalance); // walletBalance is less than total, should fail
        } catch (EmptyCartException | InvalidPaymentException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // clear cart and try checkout on empty cart
        removeFromCart("Laptop");
        try {
            calculateTotal();
        } catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
