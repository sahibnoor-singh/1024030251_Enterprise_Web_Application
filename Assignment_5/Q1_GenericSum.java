// Q1 - Generic method to sum an array of numbers
public class Q1_GenericSum {

    static <T extends Number> double sum(T[] arr) {
        double total = 0;
        for (T x : arr) {
            total += x.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        Float[] floats = {1.5f, 2.5f, 3.5f};
        Double[] doubles = {10.25, 20.5, 30.75};

        System.out.println("Integer sum: " + sum(ints));
        System.out.println("Float sum: " + sum(floats));
        System.out.println("Double sum: " + sum(doubles));
    }
}
