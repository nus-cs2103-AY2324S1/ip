package duke;

public class Messages {

    /**
     * Constructor for the Message class
     * Print the greeting message and create a new Ui object to allow for user input
     */
    public Messages() {
        Messages.Greet();
        Ui ui = new Ui();
    }

    /**
     * Print the greeting message when called
     */
    public static final void Greet() {
        System.out.println("Hello! I'm Chatty!");
        System.out.println("What can I do for you today? ");
    }

    /**
     * Print the exit message when called
     */
    public static final void Exit() {
        System.out.println("Bye bye! Hope to see you again soon");
    }
}
