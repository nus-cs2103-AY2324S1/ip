/**
 * This class encapsulates the functions used to print statements when user enters and exits the program.
 *
 */
public class Greeting {
    static final String linebreak = "____________________________________________________________";
    /**
     * This method prints out the line when user start the program
     *
     */
    public static void greet() {
        UI.printMessage("Hello! I'm chatbot\nWhat can I do for you?");
    }

    /**
     * This method prints out the line when user exit the program
     *
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!";
        UI.printMessage(bye);
    }
}
