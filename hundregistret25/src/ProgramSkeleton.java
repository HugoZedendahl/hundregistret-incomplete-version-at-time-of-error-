import java.util.Scanner;

public class ProgramSkeleton {

	private static final int EXIT_COMMAND = 0;
	private static final int NAME_OF_FIRST_COMMAND = 1;
	private static final int NAME_OF_SECOND_COMMAND = 2;

	private Scanner input = new Scanner(System.in);
	// Övriga instansvariabler här...

	public void start() {
		setUp();
		runCommandLoop();
		shutDown();
	}

	private void setUp() {
		// Kod som ska köras en gång precis när programmet startar
	}

	private void runCommandLoop() {
		int command;
		do {
			command = readCommand();
			executeCommand(command);
		} while (command != EXIT_COMMAND);
	}

	private int readCommand() {
		System.out.print(">");
		return input.nextInt();
	}

	private void executeCommand(int command) {
		switch (command) {
		case NAME_OF_FIRST_COMMAND:
			// Anropa metoden som utför detta kommando
			break;
		case NAME_OF_SECOND_COMMAND:
			// Anropa metoden som utför detta kommando
			break;
		case EXIT_COMMAND:
			// Förklaring till varför detta case är tomt...
			break;
		default:
			printErrorMessage("Wrong command, try again!");
		}
	}

	private void printErrorMessage(String msg) {
		System.out.println(msg);
	}

	private void shutDown() {
		// Kod som ska köras en gång precis innan programmet avslutas
	}

	public static void main(String[] args) {
		new ProgramSkeleton().start();
	}

}
