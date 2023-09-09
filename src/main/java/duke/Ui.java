package duke;

import java.util.ArrayList;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    /**
     * Shows a loading error if failing to initialise starting classes.
     */
    public static void showLoadingError() {
        System.out.println("Unable to initialise duke.Duke.");
    }

    /**
     * Shows welcome message.
     */
    public static String showWelcomeMessage() {
        return ("Hello I'm iP");
    }

    /**
     * Shows goodbye message.
     */
    public static String showGoodbyeMessage() {
        return ("Bye!");
    }

    /**
     * Shows help message.
     */
    public static String showHelpMessage() {
        String startingLine = "Hello, I'm iP, please use one of the following commands:";
        String byeInfo = "bye: Prints a goodbye message.";
        String helpInfo = "help: Prints the list of commands.";
        String listInfo = "list: Prints the list of tasks you have.";
        String deleteInfo = "delete <task_no>: Delete the task given by the task number.";
        String markInfo = "mark <task_no>: Marks the task given by the task number as complete.";
        String todoInfo = "todo <title>: Creates a todo task.";
        String deadlineInfo = "deadline <title> /by <deadline>: Creates a deadline task with a optional deadline";
        String eventInfo = "deadline <title> /from <time> /to <time>: "
                + "Creates a event task with a optional from time and to time.";
        String findInfo = "find <keyword>: Finds all tasks that match the keyword provided.";
        String[] allInfo = {startingLine, byeInfo, helpInfo, listInfo, deleteInfo, markInfo,
                            todoInfo, deadlineInfo, eventInfo, findInfo};
        StringBuilder response = new StringBuilder();
        for (String infoLine : allInfo) {
            response.append(infoLine).append("\n");
        }
        return response.toString();
    }

    /**
     * Shows a list of tasks.
     * @param tasks Tasks to show.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("List of tasks:").append("\n");
        for (Task task : tasks) {
            response.append(task.toString()).append("\n");
        }
        response.append("You have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Returns the message to be sent to the user after a successful delete task operation.
     * @param taskDeleted The task that was deleted.
     */
    public static String deleteTask(Task taskDeleted) {
        return "Noted. I've removed this task:" + "\n"
                + taskDeleted.toString() + "\n";
    }

    /**
     * Returns the message to be sent to the user after a successful mark task operation.
     * @param taskCompleted The task that was marked.
     */
    public static String markTask(Task taskCompleted) {
        return "Nice! I've marked this task as done:" + "\n"
                + taskCompleted.toString() + "\n";
    }

    /**
     * Shows a list of given tasks to the user.
     * @param tasks Tasks to be shown to the user.
     */
    public static String foundTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:").append("\n");
        for (Task task : tasks) {
            response.append(task.toString());
        }
        return response.toString();
    }

    public static String invalidCommand() {
        return "Invalid command, please try again.";
    }
}
