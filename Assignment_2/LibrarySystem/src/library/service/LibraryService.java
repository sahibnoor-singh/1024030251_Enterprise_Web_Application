package library.service;

import library.model.LibraryResource;
import library.model.Printable;

public class LibraryService {

    public void displayResource(LibraryResource resource) {
        if (resource instanceof Printable) {
            ((Printable) resource).printDetails();
        }
    }

    public double calculateTotalFine(LibraryResource[] resources, int[] overdueDays) {
        double total = 0.0;
        for (int i = 0; i < resources.length; i++) {
            total += resources[i].calculateFine(overdueDays[i]);
        }
        return total;
    }
}
