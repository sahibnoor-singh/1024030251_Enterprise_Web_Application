// Q3 - Generic Result class for student marks
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private String name;
    private List<T> marks;

    Result(String name, List<T> marks) {
        this.name = Objects.requireNonNull(name, "Name is required");
        this.marks = Objects.requireNonNull(marks, "Marks are required");
    }

    Optional<Double> getAverage() {
        if (marks.isEmpty()) {
            return Optional.empty();
        }
        double total = 0;
        for (T m : marks) {
            total += m.doubleValue();
        }
        return Optional.of(total / marks.size());
    }

    boolean hasPassed(double threshold) {
        return getAverage().map(avg -> avg >= threshold).orElse(false);
    }

    void display(double threshold) {
        System.out.println("Student: " + name);
        System.out.println("Marks: " + marks);
        getAverage().ifPresentOrElse(
            avg -> {
                System.out.println("Average: " + avg);
                System.out.println("Status: " + (hasPassed(threshold) ? "Pass" : "Fail"));
            },
            () -> System.out.println("Marks not available")
        );
        System.out.println();
    }
}

public class Q3_ResultGeneric {

    public static void main(String[] args) {
        List<Integer> m1 = new ArrayList<>(List.of(78, 85, 92));
        List<Double> m2 = new ArrayList<>(List.of(35.5, 28.0, 41.5));
        List<Float> m3 = new ArrayList<>();

        new Result<>("Aman", m1).display(40);
        new Result<>("Riya", m2).display(40);
        new Result<>("Karan", m3).display(40);

        try {
            new Result<Integer>(null, m1);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
