// Q4 - Event manager using LocalDate and LocalTime
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4_EventManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Event name: ");
        String name = sc.nextLine();

        System.out.print("Event date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Start time (HH:mm): ");
        LocalTime start = LocalTime.parse(sc.nextLine().trim());

        System.out.print("Venue (press enter to skip): ");
        String v = sc.nextLine().trim();
        Optional<String> venue = v.isEmpty() ? Optional.empty() : Optional.of(v);

        LocalDate today = LocalDate.now();

        System.out.println("\n--- Event Details ---");
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Start: " + start);
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        if (date.isAfter(today)) {
            System.out.println("The event is in the future");
            long days = ChronoUnit.DAYS.between(today, date);
            System.out.println("Days remaining: " + days);
        } else if (date.isEqual(today)) {
            System.out.println("The event is today");
            System.out.println("Days remaining: 0");
        } else {
            System.out.println("The event is in the past");
        }

        // 2 hour event
        LocalTime end = start.plusHours(2);
        System.out.println("End time: " + end);

        sc.close();
    }
}
