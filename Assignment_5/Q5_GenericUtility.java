// Q5 - Generic utility class
import java.util.Objects;
import java.util.Optional;

class GenericUtil {

    static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5_GenericUtility {

    public static void main(String[] args) {
        // isEqual
        System.out.println("isEqual(\"java\", \"java\"): " + GenericUtil.isEqual("java", "java"));
        System.out.println("isEqual(10, 20): " + GenericUtil.isEqual(10, 20));
        System.out.println("isEqual(2.5, 2.5): " + GenericUtil.isEqual(2.5, 2.5));

        // getDefault
        Optional<String> name = Optional.of("Sahib");
        Optional<String> empty = Optional.empty();
        System.out.println("getDefault(name): " + GenericUtil.getDefault(name, "Unknown"));
        System.out.println("getDefault(empty): " + GenericUtil.getDefault(empty, "Unknown"));
        System.out.println("getDefault(Optional<Integer>): " + GenericUtil.getDefault(Optional.<Integer>empty(), 0));

        // max
        System.out.println("max(15, 42): " + GenericUtil.max(15, 42));
        System.out.println("max(\"apple\", \"mango\"): " + GenericUtil.max("apple", "mango"));
        System.out.println("max(3.14, 2.71): " + GenericUtil.max(3.14, 2.71));

        // isNull
        String s = null;
        System.out.println("isNull(null): " + GenericUtil.isNull(s));
        System.out.println("isNull(\"hello\"): " + GenericUtil.isNull("hello"));
    }
}
