// Q4 - Student result processing with custom InvalidMarksException
import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class Q4_StudentResult {

    static void validateMarks(int marks) throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Invalid marks: " + marks + " (must be between 0 and 100)");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSubjects = 3;
        int[] marks = new int[numSubjects];
        boolean allValid = true;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();

            try {
                validateMarks(marks[i]);
            } catch (InvalidMarksException e) {
                System.out.println("Error: " + e.getMessage());
                allValid = false;
            }
        }

        if (allValid) {
            int total = 0;
            for (int m : marks) {
                total += m;
            }
            double percentage = (double) total / numSubjects;

            String grade;
            if (percentage >= 90) grade = "A";
            else if (percentage >= 75) grade = "B";
            else if (percentage >= 50) grade = "C";
            else grade = "F";

            System.out.println("Total: " + total);
            System.out.println("Percentage: " + percentage);
            System.out.println("Grade: " + grade);
        } else {
            System.out.println("Cannot calculate result, invalid marks entered.");
        }

        sc.close();
    }
}
