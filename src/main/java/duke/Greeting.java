package duke;


import duke.ui.UI;

/**
 * This class encapsulates the functions used to print statements when user enters and exits the program.
 *
 */
public class Greeting {

    /**
     * This method prints out the line when user start the program
     *
     */
    public static String greet() {
        UI.printMessage("Hello! I'm chatbot\nWhat can I do for you?");
        return "Hello! I'm chatbot\nWhat can I do for you?";
    }

    /**
     * This method prints out the line when user exit the program
     *
     */
    public static String bye() {
        String bye = "Bye. Hope to see you again soon!";
        UI.printMessage(bye);
        return bye;
    }
}
