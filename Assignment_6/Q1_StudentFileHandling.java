// Q1 - Student records using byte and character streams
import java.io.*;

public class Q1_StudentFileHandling {
    public static void main(String[] args) {
        // Copy the sample binary file
        try (FileInputStream input = new FileInputStream("sample.bin");
             FileOutputStream output = new FileOutputStream("copy.bin")) {
            int data;
            while ((data = input.read()) != -1) {
                output.write(data);
            }
            System.out.println("Binary file copied to copy.bin");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }

        // Write student details
        try (FileWriter writer = new FileWriter("students.txt")) {
            writer.write("Roll No: 101, Name: Aman, Course: BCA\n");
            writer.write("Roll No: 102, Name: Simran, Course: BCA\n");
            System.out.println("Student details saved to students.txt");
        } catch (IOException e) {
            System.out.println("Error writing student details: " + e.getMessage());
        }

        // Read student details
        try (FileReader reader = new FileReader("students.txt")) {
            int character;
            System.out.println("\nStudent details:");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error reading student details: " + e.getMessage());
        }
    }
}
