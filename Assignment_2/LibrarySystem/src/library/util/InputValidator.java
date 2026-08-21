package library.util;

public class InputValidator {

    public static boolean validateResourceId(int resourceId) {
        return resourceId > 0;
    }

    public static boolean validateFineDays(int overdueDays) {
        // days must be postive
        return overdueDays > 0;
    }
}
