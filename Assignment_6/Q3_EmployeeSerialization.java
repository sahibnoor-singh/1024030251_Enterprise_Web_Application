// Q3 - Save and read employee records using serialization
import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name
                + ", Department: " + department + ", Salary: " + salary);
    }
}

public class Q3_EmployeeSerialization {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee(101, "Aman", "IT", 40000),
            new Employee(102, "Simran", "HR", 35000),
            new Employee(103, "Rahul", "Finance", 45000)
        };

        // Save the employee objects
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("employees.dat"))) {
            output.writeObject(employees);
            System.out.println("Employee records saved.");
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
            return;
        }

        // Read them back
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("employees.dat"))) {
            Employee[] savedEmployees = (Employee[]) input.readObject();
            System.out.println("\nEmployee records:");
            for (Employee employee : savedEmployees) {
                employee.display();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading records: " + e.getMessage());
        }
    }
}
