package ui;

import java.util.Scanner;

public class Ui {

    /**
     * Initialize the Ui object.
     */
    public Ui() {

    }

    /**
     * Scans the next input of the user.
     * @return The input of the user.
     */
    public String nextInput() {
        Scanner scanObj = new Scanner(System.in);
        return scanObj.nextLine();
    }

    /**
     * Prints the greeting of the program.
     */
    public void greet() {
        System.out.println("Hello. I am Luxion. \n" +
                "What can I do for you?");
    }

    /**
     * Prints the exit of the program.
     */
    public void exit() {
        System.out.println("Bye. See you soon.");
    }
}
