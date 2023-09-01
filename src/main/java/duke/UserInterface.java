package duke;

import java.util.Scanner;

/**
 * Represents the user interface of an application.
 */
public class UserInterface {
    private Scanner userInput;
    private final String lineDivider = "---------------------------------------------";
    public UserInterface() {
        this.userInput = new Scanner(System.in);
    }

    public String input() {
        return userInput.nextLine();
    }

    /**
     * Takes a string and prints it out to the system, while formatting it with line dividers.
     * @param output the string to be printed.
     */
    public void output(String output) {
        System.out.println(output);
        System.out.println(lineDivider);
    }

}
