package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.service.LibraryService;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];
        resources[0] = new Book(101, "Effective Java", "Joshua Bloch");
        resources[1] = new DigitalResource(102, "Cloud Computing Basics", "Rajkumar Buyya");
        resources[2] = new Book(103, "Clean Code", "Robert C. Martin");
        resources[3] = new DigitalResource(104, "AI for Everyone", "Andrew Ng");
        // add one more just in case
        resources[4] = new Book(105, "Design Patterns", "Erich Gamma");

        int[] overdueDays = {3, 0, 10, 5, 2};

        LibraryService service = new LibraryService();

        System.out.println("Library: " + LibraryResource.getLibraryName());
        System.out.println("=================================");

        for (int i = 0; i < resources.length; i++) {
            LibraryResource resource = resources[i];

            boolean idValid = InputValidator.validateResourceId(resource.getResourceId());
            boolean daysValid = InputValidator.validateFineDays(overdueDays[i]);

            if (idValid && daysValid) {
                service.displayResource(resource);
                System.out.printf("Ovedue Days: %d, Fine: Rs. %.2f%n%n",
                        overdueDays[i], resource.calculateFine(overdueDays[i]));
            } else {
                System.out.println("Invalid data for resource id: " + resource.getResourceId());
            }
        }

        double totalFine = service.calculateTotalFine(resources, overdueDays);
        System.out.printf("Total Fine (all reosurces): Rs. %.2f%n", totalFine);

        LibraryResource.displayTotalResources();
    }
}
