// Q6 - Login system with exception-based validation
class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

public class Q6_LoginSystem {

    static final String CORRECT_USERNAME = "sahib";
    static final String CORRECT_PASSWORD = "pass123";
    static final int MAX_ATTEMPTS = 3;

    static int failedAttempts = 0;
    static boolean isLocked = false;

    static void login(String username, String password) throws InvalidUsernameException,
            InvalidPasswordException, AccountLockedException {

        if (isLocked) {
            throw new AccountLockedException("Account is locked due to too many failed attempts");
        }

        try {
            if (!username.equals(CORRECT_USERNAME)) {
                throw new InvalidUsernameException("Username not found");
            }
            if (!password.equals(CORRECT_PASSWORD)) {
                throw new InvalidPasswordException("Incorrect password");
            }

            System.out.println("Login successful!");
            failedAttempts = 0;

        } catch (InvalidUsernameException | InvalidPasswordException e) {
            failedAttempts++;
            System.out.println("Login failed: " + e.getMessage());

            if (failedAttempts >= MAX_ATTEMPTS) {
                isLocked = true;
                System.out.println("Maximum attempts reached. Account locked.");
            }
            throw e;

        } finally {
            System.out.println("Attempt number: " + failedAttempts);
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"sahib", "wrongpass"},
                {"wronguser", "pass123"},
                {"sahib", "wrongagain"},
                {"sahib", "pass123"} // should be locked out by now
        };

        for (String[] attempt : attempts) {
            try {
                login(attempt[0], attempt[1]);
            } catch (AccountLockedException e) {
                System.out.println("Blocked: " + e.getMessage());
            } catch (InvalidUsernameException | InvalidPasswordException e) {
                // already printed inside login()
            }
        }
    }
}
