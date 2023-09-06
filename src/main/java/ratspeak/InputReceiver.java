package ratspeak;

import java.util.Scanner;
public class InputReceiver {
    private final Scanner scanner;

    /**
     * Initialize InputReceiver object that focus on taking in inputs
     */
    public InputReceiver() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input in a line as a string
     * @return string of user input
     */
    public String getInput() {
        return this.scanner.nextLine();

    }

    /**
     * close the scanner object in the InputReceiver
     */
    public void closeParser() {
        scanner.close();
    }

}
