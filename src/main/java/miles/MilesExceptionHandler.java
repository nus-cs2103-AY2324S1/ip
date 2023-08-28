package miles;

/**
 * Class that handles exceptions that are raised or appear in the program.
*/
public class MilesExceptionHandler {
    /**
     * Prints an error message when the user inputs an empty string.
     */
    public static void handleEmptyInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" Input a task my brother.");
        ui.showLine();
    }

    /**
     * Prints an error message when the user inputs an command that is not handled by the parser.
     */
    public static void handleUnseenInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" I'm sorry brother, I do not have a Scooby :-(");
        ui.showLine();
    }

    /**
     * Prints an error message when given a string that usually is the message of an exception that
     * raised or appears in the program.
     * 
     * @param s the error message from an exception that is raised or appears in the program.
     */
    public static void printErrorMsg(String s) {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" " + s);
        ui.showLine();
    }

    /**
     * Prints an error message when the user inputs a command with a task number that is out of 
     * range, such as a negative number or a number that is higher than the number of tasks in the 
     * task list.
     * 
     * @param taskNum task number in the command that is out of range
    */
    public static void handleTaskNumOutOfBounds(int taskNum) {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" There is no task " + taskNum + ", friend.");
        ui.showLine();
    }
}
