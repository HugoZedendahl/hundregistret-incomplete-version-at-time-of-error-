import java.util.Arrays;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        String test = "this is my string AND i want the formatter tO fUnCtion";
        stringFormatter(test);
        Dog dog = new Dog(test, test, 12, 13);
        System.out.println(dog.getTailLength());
    }
    static String stringFormatter(String input){
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                     .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                     .collect(Collectors.joining(" "));
    }
}
