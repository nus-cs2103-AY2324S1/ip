package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui class
     * Create a Scanner object
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get the next line from the user
     *
     * @return the user input
     */
    public String getInput() {
        return this.scanner.nextLine();
    }

}
