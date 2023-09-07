package bee.ui;

import java.util.ArrayList;

import bee.BeeException;
import bee.Task;


/**
 * Represents the User Interface (UI) of the chatbot.
 * Provides methods to display greetings, loading errors, and farewell messages.
 */
public class Ui {

    /**
     * Displays a greeting message along with the chatbot's logo.
     */
    public static String greet() {
        String logo = "\n"
                + "__________\n"
                + "\\______   \\ ____   ____\n"
                + " |    |  _// __ \\_/ __ \\\n"
                + " |    |   \\  ___/\\  ___/\n"
                + " |______  /\\___  >\\___  >\n"
                + "        \\/     \\/     \\/\n";
        return "Hello! I'm" + logo + "~Bzzzz~ What may I assist you with today? ~Bzzzz~\n";
    }

    /**
     * Displays an error message indicating a failure to load tasks.
     */
    public String showLoadingError() {
        return "~Bzzzz~ Oh no! We failed to load the tasks\n";
    }

    /**
     * Displays a farewell message to bid the user goodbye.
     */
    public static String farewell() {
        return "Bye-bye! Have a great day! ~Bzzz~";
    }

    /**
     * Prints the error message.
     *
     * @param err The error message.
     */
    public static String returnErrorString(BeeException err) {
        return err.toString();
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param tsk
     * @param taskNumber
     */
    public static String addTask(Task tsk, int taskNumber) {
        return "Got it. I've added this task:\n"
                + tsk.toString()
                + "\nNow you have "
                + taskNumber
                + " tasks in the list.  ~Bzzz~";
    }

    /**
     * Deletes a Task object from the specified list of tasks.
     *
     * @param task The task to be deleted.
     * @param taskNumber The number of tasks in the list.
     */
    public static String deleteTask(Task task, int taskNumber) {
        return "Okies~. I've removed this task:\n" + task.toString() + "\nNow you have "
                + taskNumber + " tasks in the list.";
    }

    /**
     * Lists all the tasks in the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public static String listAllTasks(ArrayList<Task> tasks) {
        String res = "Here are the tasks in your list:  ~Bzzz~";
        for (int i = 0; i < tasks.size(); i++) {
            res += "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }
        return res;
    }

    /**
     * Marks a Task object in the specified list of tasks as done.
     *
     * @param task The task to be marked as done.
     */
    public static String setTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Marks a Task object in the specified list of tasks as not done.
     *
     * @param task The task to be marked as not done.
     */
    public static String setTaskNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString() + " ~Bzzzz";
    }

    /**
     * Finds the tasks that contain the specified keyword.
     *
     * @param tasks The list of tasks that contain the specified keyword.
     */
    public static String findTasks(ArrayList<Task> tasks) {
        String res = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            res += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return res;
    }

}
