// Q1 - Course registration portal
import java.util.*;

class Course {
    String code;
    String name;
    int capacity;
    HashSet<String> students = new HashSet<>();
    ArrayList<String> prereqs = new ArrayList<>();
    LinkedList<String> waitlist = new LinkedList<>();

    Course(String code, String name, int capacity, String... prereqs) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.prereqs.addAll(Arrays.asList(prereqs));
    }

    boolean isFull() {
        return students.size() >= capacity;
    }

    public String toString() {
        return code + " (" + name + ") seats " + students.size() + "/" + capacity
                + " prereqs " + prereqs + " waitlist " + waitlist;
    }
}

public class Q1_CourseRegistration {

    static HashMap<String, Course> courses = new HashMap<>();
    static HashMap<String, HashSet<String>> completed = new HashMap<>();

    static void markCompleted(String id, String code) {
        completed.computeIfAbsent(id, k -> new HashSet<>()).add(code);
    }

    static String register(String id, String code) {
        Course c = courses.get(code);
        if (c == null) {
            return "Course " + code + " not found";
        }
        if (c.students.contains(id)) {
            return id + " is already enrolled in " + code;
        }
        if (c.waitlist.contains(id)) {
            return id + " is already on the waitlist for " + code;
        }

        // prereqs
        HashSet<String> done = completed.getOrDefault(id, new HashSet<>());
        for (String p : c.prereqs) {
            if (!done.contains(p)) {
                return id + " has not completed " + p;
            }
        }

        if (!c.isFull()) {
            c.students.add(id);
            return id + " enrolled in " + code;
        }
        c.waitlist.add(id);
        return code + " is full, " + id + " added to waitlist (position " + c.waitlist.size() + ")";
    }

    static String drop(String id, String code) {
        Course c = courses.get(code);
        if (c == null) {
            return "Course " + code + " not found";
        }
        if (c.waitlist.remove(id)) {
            return id + " removed from waitlist of " + code;
        }
        if (!c.students.remove(id)) {
            return id + " is not enrolled in " + code;
        }

        String msg = id + " dropped " + code;
        // promote
        while (!c.isFull() && !c.waitlist.isEmpty()) {
            String next = c.waitlist.removeFirst();
            c.students.add(next);
            msg += "\n" + next + " moved from waitlist to " + code;
        }
        return msg;
    }

    public static void main(String[] args) {
        courses.put("CS101", new Course("CS101", "Intro to Programming", 2));
        courses.put("CS201", new Course("CS201", "Data Structures", 2, "CS101"));
        courses.put("CS301", new Course("CS301", "Algorithms", 1, "CS101", "CS201"));

        for (String s : new String[]{"S1", "S2", "S3", "S4"}) {
            markCompleted(s, "CS101");
        }
        markCompleted("S1", "CS201");
        markCompleted("S2", "CS201");

        System.out.println("--- Registration ---");
        System.out.println(register("S1", "CS101"));
        System.out.println(register("S2", "CS101"));
        System.out.println(register("S3", "CS101"));
        System.out.println(register("S4", "CS101"));
        System.out.println(register("S1", "CS101"));
        System.out.println(register("S5", "CS201"));
        System.out.println(register("S1", "CS301"));
        System.out.println(register("S2", "CS301"));
        System.out.println(register("S1", "CS999"));
        System.out.println(courses.get("CS101"));

        System.out.println("\n--- Drop ---");
        System.out.println(drop("S1", "CS101"));
        System.out.println(courses.get("CS101"));
        System.out.println(drop("S4", "CS101"));
        System.out.println(drop("S2", "CS101"));
        System.out.println(courses.get("CS101"));
        System.out.println(drop("S1", "CS301"));
        System.out.println(courses.get("CS301"));
    }
}
