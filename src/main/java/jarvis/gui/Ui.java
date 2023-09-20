package jarvis.gui;

import java.util.ArrayList;

import jarvis.tasks.Task;

/**
 * The user interface class responsible for displaying information to the user on the CLI.
 */
public class Ui {

    /**
     * ASCII Art Generated from <a href="http://patorjk.com/software/taag/">...</a>
     */
    private static final String LOGO_TEST = null;

    /**
     * Default date and time format used n Jarvis app.
     */
    private static final String DATE_TIME_FORMAT = "MMM dd yyyy HHmm";

    public static String getDefaultDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

    /**
     * Prints the intro message when Jarvis starts.
     */
    public String printIntro() {
        return "Hi Master! I'm your personal assistant: JARVIS! \n" + "\n" + LOGO_TEST + "\n"
                + "    How can I serve you today? \n";
    }

    /**
     * Prints response message to the user's command.
     */
    public String printResponse(String response) {
        return response;
    }

    /**
     *
     */
    public String printBye() {
        return "Bye Master. It has been my honour to serve you!";
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks An ArrayList of Task objects to be printed.
     * @return A formatted string representing the list of tasks.
     */
    public String printTasks(ArrayList<Task> tasks) {
        assert tasks.size() > 0 : "A list of tasks should be provided";
        String outputString;
        outputString = "    Tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputString += (i + 1) + ". " + task.toString() + "\n";
        }
        return outputString;
    }

    /**
     * Prints the status of a task, indicating whether it is completed or uncompleted.
     *
     * @param task The Task whose status is to be printed.
     * @return A string indicating the change in status of the task.
     */
    public String printTaskStatus(Task task) {
        return "Understood Master. I've marked this task as " + (task.isCompleted() ? "completed" : "uncompleted")
                + "\n" + "\t" + task.toString();
    }

    /**
     * Prints an error message.
     */
    public String printError(String error) {
        return error + "\n";
    }
}
