package duke;


import duke.ui.UI;

/**
 * This class encapsulates the functions used to print statements when user enters and exits the program.
 *
 */
public class Greeting {
    public static final String BYE = "Bye. Hope to see you again soon!";
    private static final String GREET = "Hello! I'm chatbot\nWhat can I do for you?";


    /**
     * This method prints out the line when user start the program
     *
     */
    public static String greet() {
        UI.printMessage(GREET);
        return GREET;
    }

    /**
     * This method prints out the line when user exit the program
     *
     */
    public static String bye() {
        UI.printMessage(BYE);
        return BYE;
    }
}
