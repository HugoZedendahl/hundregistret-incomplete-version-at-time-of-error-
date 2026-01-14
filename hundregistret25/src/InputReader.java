import java.util.Scanner;

public class InputReader {
    private static boolean instanceTracker = false;
    private Scanner input;

    public InputReader(Scanner scanner) {
        if (instanceTracker == true) {
            throw new IllegalStateException("internal reader error");
        }
        instanceTracker = true;
        this.input = scanner;
    }

    public InputReader() {
        this(new Scanner(System.in));
    }

    public int scanInt(String prompt) {
        int output;
        do {
            System.out.println(prompt + "?>");
            output = input.nextInt();
            input.nextLine();
            if (output < 0) {
                System.out.println("Error: number cannot be negative, try again.");
            }
        } while (output < 0);
        return output;
    }

    public double scanDouble(String prompt) {
        double output;
        do {
            System.out.println(prompt + "?>");
            output = input.nextDouble();
            input.nextLine();
            if (output < 0) {
                System.out.println("Error: number cannot be negative, try again.");
            }
        } while (output < 0);
        return output;
    }

    public String scanString(String prompt) {
        String output;
        do {
            System.out.println(prompt + "?>");
            output = input.nextLine();
            if (output.trim().isEmpty()) {
                System.out.println("Error: must not be empty or blank, try again.");
            }
        } while (output.trim().isEmpty());
        return output.trim();
    }

}
