import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatter {
    public String format(String input) {
        {
            return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                    .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                    .collect(Collectors.joining(" "));
        }

        // i found the method above at
        // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
        // the arrays.stream wrappes the split strings string into smaller strings using
        // the "\\s+"
        // regex.i modified it with .trim() and .toLowerCase() to suit
        // requirements
        // the map function iterates through words and merges the capitalized first
        // character together with the
        // rest of the substring
        // the collect functions groups all strings back into a singular, properly
        // formatted string.
    }
}
