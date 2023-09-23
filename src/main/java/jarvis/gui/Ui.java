package jarvis.gui;

import java.util.ArrayList;

import jarvis.tasks.Task;

/**
 * The user interface class responsible for displaying information to the user on the CLI.
 */
public class Ui {
    /**
     * Default date and time format used n Jarvis app.
     */
    private static final String DATE_TIME_FORMAT = "MMM dd yyyy HHmm";
    private static final String HELP_MENU = "Here are the list of quick commands to get started with:\n"
            + "1. Add Todo Task: [todo TASK_DESCRIPTION]\n"
            + "2. Add Deadline Task: [deadline TASK_DESCRIPTION by DUE_DATE]\n"
            + "3. Add Event Task: [event TASK_DESCRIPTION from START_DATE_TIME to END_DATE_TIME]\n"
            + "4. List Tasks: [list]\n"
            + "5. Delete Task: [delete INDEX]\n"
            + "6. Mark Task as completed/incomplete: [mark/unmark INDEX]\n"
            + "7. Find Tasks: [find KEYWORD]\n"
            + "8. Sort Deadline Tasks: [sort deadline]\n"
            + "9. Help Menu: [help]\n"
            + "10. Exit App: [bye]\n";

    public static String getDefaultDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

    /**
     * Prints the intro message when Jarvis starts.
     */
    public String printIntro() {
        return "Hi Master! I'm your personal assistant: JARVIS! \n"
                + "    How can I serve you today? \n\n" + HELP_MENU;
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

    /**
     * Generates a formatted string to display a list of filtered tasks.
     *
     * @param title A tag representing the filter featured.
     * @param tasks An ArrayList of Task objects representing the filtered tasks.
     * @return A formatted string displaying the filtered tasks with the specified title.
     */
    public String printFilteredTasks(String title, ArrayList<Task> tasks) {
        String filterTasks = printTasks(tasks);
        return "Filtered List: " + title
                + "\n\n" + filterTasks;
    }

    public String printHelpMessage() {
        return HELP_MENU;
    }
}
