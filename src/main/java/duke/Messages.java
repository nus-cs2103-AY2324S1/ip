package duke;

/**
 * Message class is responsible for printing the relevant messages
 */
public class Messages {

    /**
     * Constructor for the Message class
     * Print the greeting message and create a new Ui object to allow for user input
     */
    public Messages() {
        Messages.greet();
        Ui ui = new Ui();
    }

    /**
     * Print the greeting message when called
     */
    public static final void greet() {
        System.out.println("Hello! I'm Chatty!");
        System.out.println("What can I do for you today? ");
    }

    /**
     * Print the exit message when called
     */
    public static final void exit() {
        System.out.println("Bye bye! Hope to see you again soon");
    }

    /**
     * Print the find nothing string when called
     * @return find nothing String
     */
    public static final String findNothing() {
        return "There is no task matching your description";
    }

    /**
     * Print the found something string when called
     *
     * @return the found something String
     */
    public static final String keywordFound() {
        return "Here are the matching tasks in your list:" + "\n";
    }
}
