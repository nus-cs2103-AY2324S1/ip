package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the interactions with the user by displaying lines into the output.
 */
public class Ui {

    /**
     * Prints the welcome message when the user starts the program.
     */
    public String getWelcomeMessage() {
        return "WEEWOOWEEWOO WELCOME! I'm Siren\nWhat can I do for you?";
    }

    /**
     * Prints the goodbye message when the user exits the program.
     */
    public static String getGoodbyeMessage() {
        return "WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!";
    }

    /**
     * Prints the exception's message.
     *
     * @param e the exception that will have its message printed.
     */
    public static String showExceptionError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints the message to indicate a saved file is found.
     */
    public static String savedFileFound() {
        return "BLINGBLING! You have a saved file! Displaying the contents to you (if any)!\n";
    }

    /**
     * Prints the message to indicate there is no saved file found.
     */
    public static String savedFileNotFound() {
        return "OH NO! I couldn't find a \"tasks.txt\" file in your data directory, "
                + "I'll be creating one for you!\n";
    }

    /**
     * Prints the message to indicate the specific directory cannot be found.
     */
    public static String directoryNotFound() {
        return "OH NO! I couldn't find a \"data\" directory in your project root directory, "
                + "I'll be creating one for you!\n";
    }

    /**
     * Prints the message when the user deletes a task.
     *
     * @param description the description of task that was deleted.
     * @param arraySize the number of tasks left.
     */
    public static String deleteTaskOutput(String description, int arraySize) {
        return "ALRIGHTY! I've removed this task:\n" + description
                + "\nNow you have " + arraySize + " tasks in the list.";
    }

    /**
     * Prints the message when the user adds a task.
     *
     * @param taskList the TaskList object that contains the array list containing the tasks.
     */
    public static String addTaskOutput(TaskList taskList) {
        return "DINGDONG GOT IT! I've added this task:\n"
                + taskList.taskArray.get(taskList.taskArray.size() - 1)
                + "\nNow you have " + taskList.taskArray.size() + " tasks in the list.";
    }

    /**
     * Prints the message when the user marks a task.
     *
     * @param marked true if the task was previously marked, false otherwise.
     */
    public static String getTaskMarked(boolean marked) {
        if (marked) {
            return "WEEYA! Task was already marked as done!\n";
        }
        return "GOTCHYA! I've marked this task as done!\n";

    }

    /**
     * Prints the message when the user unmarks a task.
     *
     * @param notMarked true if the task was previously unmarked, false otherwise.
     */
    public static String getTaskNotMarked(boolean notMarked) {
        if (notMarked) {
            return "OOPSIE! Task was already marked as not done!\n";
        }
        return "HONKHONK! I've marked this task as not done yet!\n";
    }

    /**
     * Prints the message when the array list containing the tasks.
     *
     * @param taskArray the TaskList object that contains the array list containing the tasks.
     * @param isMatch true if the user command is a "find" command, false otherwise.
     */
    public static String showTaskList(ArrayList<Task> taskArray, boolean isMatch) {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskArray.isEmpty() && !isMatch) {
            stringBuilder.append("HEYYYYYYYY! There's nothing to show in your list!\n");
        } else if (taskArray.isEmpty()) {
            stringBuilder.append("HAIYAAAA! There's nothing in your list that matches the keyword!\n");
        }
        if (!taskArray.isEmpty() && !isMatch) {
            stringBuilder.append("WHEET WHEET WHEET! Here are the tasks in your list:\n");
        } else if (!taskArray.isEmpty()) {
            stringBuilder.append("HOOOOOYEAAAAA! Here are the matching tasks in your list:\n");
        }
        for (int i = 0; i < taskArray.size(); i++) {
            stringBuilder.append(String.format("%d", i + 1)).append(".").append(taskArray.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
}
