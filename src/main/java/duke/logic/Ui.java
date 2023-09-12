package duke.logic;

import java.util.Scanner;

/**
 * Class to handle with user inputs.
 */
public class Ui {
    Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints error message when data file is empty or fails to load.
     */
    public void showLoadingError() {
        System.out.println("An error has occurred while attempting"
                + " to load the data file. A new file will be created!");
    }

    /**
     * Closes the scanner when chatbot ends.
     */
    public void end() {
        this.sc.close();
    }

}
