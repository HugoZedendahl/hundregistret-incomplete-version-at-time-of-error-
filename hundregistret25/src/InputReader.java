import java.util.Scanner;

public class InputReader {
    private static boolean instanceTracker;
    private Scanner input;

    public InputReader(Scanner scanner) {
        this.input = scanner;
        if (instanceTracker) {
            throw new IllegalStateException("internal reader error");
        }
        instanceTracker = true;
    }

    public InputReader() {
        this(new Scanner(System.in));
    }

    public int scanInt(String prompt) {
        try {
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
        } catch (Exception e) {
            clearScanner();
            throw new IllegalStateException("error, please enter a number");
        }
    }

    public double scanDouble(String prompt) {
        try {
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
        } catch (Exception e) {
            clearScanner();
            throw new IllegalStateException("error, please enter a number");
        }
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

    private void clearScanner() {
        if (this.input.hasNextLine()) {
            this.input.nextLine();

        }
    }
}
