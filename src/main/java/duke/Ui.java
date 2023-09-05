package duke;

/**
 * Class to take care of UI
 */
public class Ui {
    private String divider = "------------------------------------\n";
    private String greet = divider
            + "Hello! I'm Khaleelur!\n"
            + "What can I do for you?\n "
            + divider;

    private String exit = divider
            + "Bye. Hope to see you again soon!\n"
            + divider;
    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println(greet);
    }

    /**
     * Displays an exit message.
     */
    public void exit() {
        System.out.println(exit);
    }

    /**
     * Prints the provided string surrounded by a divider.
     *
     * @param toPrint The string to be printed.
     */
    public void print(String toPrint) {
        System.out.println(divider + toPrint + "\n" + divider);
    }

    /**
     * Displays an error message related to loading.
     *
     * @param e The exception containing the error message.
     */
    public void showLoadingError(Exception e) {
        System.out.println(divider + e.getMessage() + "\n" + divider);
    }
}
