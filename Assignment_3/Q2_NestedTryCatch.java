// Q2 - Nested try-catch blocks
public class Q2_NestedTryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try block started");

            try {
                int[] arr = new int[3];
                arr[4] = 10; // inner exception - ArrayIndexOutOfBoundsException
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: " + e.getMessage());
                // generate another exception inside inner catch
                int x = 10 / 0;
            }

        } catch (ArithmeticException e) {
            System.out.println("Outer catch: " + e.getMessage());
        }

        System.out.println("Program continues after nested try-catch");
    }
}
