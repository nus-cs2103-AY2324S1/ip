package duke.ui;

/**
 * Class to take care of UI
 */
public class Ui {
    private String greetMessage = "SUI, Hello! I'm Khaleelur!\n" + "What can I do for you?\n ";
    private String exitMessage = "SUI, Bye. Hope to see you again soon!\n";
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
}
