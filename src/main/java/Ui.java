import java.util.Scanner;

public class Ui {

	/**
	 * Prints a line
	 */
	void printLine() {
		System.out.println("____________________________________________________________");
	}

	/**
	 * Prints a line
	 */
	void greet() {
		printLine();
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		printLine();
	}

	/**
	 * The exit command when user types "bye"
	 **/
	public void exit() {
		System.out.println("I shall now take my leave. Farewell!");
	}

}
