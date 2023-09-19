package functions;

/**
 * A class for handling user interface messages.
 */
public class Ui {

    /**
     * Returns a welcome message to greet the user.
     *
     * @return a welcome message
     */
    public String welcome() {
        return "Hello! Welcome to cupid task tracking bot. How can I help you?";
    }

    /**
     * Returns a message indicating that a file was not found and a new one is being created.
     *
     * @return a message indicating that a file was not found and a new one is being created
     */
    public String fileNotFound() {
        return "File not found. Creating new .txt save file";
    }

    /**
     * Returns a goodbye message to bid farewell to the user.
     *
     * @return a goodbye message
     */
    public String goodbye() {
        String message = "";
        message += "Bye. Hope to see you again soon!\n";
        return message;
    }
}
