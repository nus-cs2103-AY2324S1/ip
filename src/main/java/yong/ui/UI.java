package yong.ui;

import java.util.Scanner;

/**
 * UI class to handle UI methods.
 */
public class UI {

    static Scanner reader;

    /**
     * Constructor for the UI class.
     * Initializes a scanner to read user input.
     */
    public UI() {
        this.reader = new Scanner(System.in);
    }


    /**
     * Prints out the error message.
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
