package oscar.ui;

import java.util.Scanner;

import oscar.exception.OscarException;

/**
 * Class to deal with user interaction.
 */
public class Ui {
    /**
     * Displays message to greet user.
     */
    public void greet() {
        System.out.println("Hello! This is Oscar, your friendly chatbot :)");
        System.out.println("What can Oscar do for you?\n");
    }

    /**
     * Prints error message in OscarException.
     *
     * @param e OscarException.
     */
    public String showError(OscarException e) {
        return e.getMessage();
    }
}
