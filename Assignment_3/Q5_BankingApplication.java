// Q5 - Banking application with custom exceptions
import java.util.HashMap;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

public class Q5_BankingApplication {

    static HashMap<String, Double> accounts = new HashMap<>();

    static void checkAccountExists(String accNo) throws AccountNotFoundException {
        if (!accounts.containsKey(accNo)) {
            throw new AccountNotFoundException("Account " + accNo + " does not exist");
        }
    }

    static void deposit(String accNo, double amount) throws InvalidAmountException, AccountNotFoundException {
        checkAccountExists(accNo);
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }
        accounts.put(accNo, accounts.get(accNo) + amount);
    }

    static void withdraw(String accNo, double amount) throws InvalidAmountException,
            InsufficientBalanceException, AccountNotFoundException {
        checkAccountExists(accNo);
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }
        if (accounts.get(accNo) < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account " + accNo);
        }
        accounts.put(accNo, accounts.get(accNo) - amount);
    }

    public static void main(String[] args) {
        accounts.put("101", 1000.0);

        try {
            deposit("101", 500);
            System.out.println("Balance after deposit: " + accounts.get("101"));

            withdraw("101", 2000); // should fail - insufficient balance
        } catch (InsufficientBalanceException | InvalidAmountException | AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            withdraw("999", 100); // should fail - account not found
        } catch (InsufficientBalanceException | InvalidAmountException | AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            deposit("101", -50); // should fail - invalid amount
        } catch (InsufficientBalanceException | InvalidAmountException | AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Final balance: " + accounts.get("101"));
    }
}
