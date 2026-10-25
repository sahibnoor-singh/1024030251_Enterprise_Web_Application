// Q2 - Student email using Optional
import java.util.Optional;

public class Q2_OptionalEmail {

    public static void main(String[] args) {
        Optional<String> email = Optional.of("sahib@thapar.edu");
        Optional<String> noEmail = Optional.empty();

        // ifPresent
        email.ifPresent(e -> System.out.println("Email: " + e));
        noEmail.ifPresent(e -> System.out.println("This will not print"));

        // orElse
        System.out.println("With email: " + email.orElse("default@thapar.edu"));
        System.out.println("Without email: " + noEmail.orElse("default@thapar.edu"));

        // orElseThrow
        try {
            String e = noEmail.orElseThrow(() -> new IllegalStateException("Email is missing"));
            System.out.println(e);
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
