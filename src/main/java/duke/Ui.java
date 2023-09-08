package duke;

/**
 * Class to take care of UI
 */
public class Ui {
    private String greetMessage = "Hello! I'm Khaleelur!\n" + "What can I do for you?\n ";
    private String exitMessage = "Bye. Hope to see you again soon!\n";
    /**
     * Displays a greeting message.
     */
    public String greet() {
        return greetMessage;
    }

    /**
     * Displays an exit message.
     */
    public String exit() {
        return exitMessage;
    }
    /**
     * Displays an error message related to loading.
     *
     * @param e The exception containing the error message.
     */
    public String showLoadingError(Exception e) {
        return e.getMessage();
    }
}
