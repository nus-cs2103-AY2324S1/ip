package valerie;

import java.util.ArrayList;

/**
 * The Ui class handles user interface interactions in the Duke application.
 * It provides methods to display messages, errors, and task-related information
 * to the user using the console.
 */
public class Ui {
    /**
     * Generates an error message as an ArrayList of strings.
     *
     * @param error The error message to be formatted and returned.
     * @return An ArrayList containing the formatted error message.
     */
    public static ArrayList<String> showError(String error) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(String.format("%s!", error));

        return errorMessage;
    }

    /**
     * Generates a message to indicate a task has been marked as done and returns it as an ArrayList of strings.
     *
     * @param task The task to be marked as done and included in the message.
     * @return An ArrayList containing the message indicating the marked task.
     */
    public static ArrayList<String> showMarkedTask(Task task) {
        ArrayList<String> markedTaskMessage = new ArrayList<>();
        markedTaskMessage.add("Nice! I've marked this task as done:");
        markedTaskMessage.add(task.toString());

        return markedTaskMessage;
    }

    /**
     * Generates a message to indicate a task has been marked as not done yet and returns it as an ArrayList of strings.
     *
     * @param task The task to be marked as not done yet and included in the message.
     * @return An ArrayList containing the message indicating the unmarked task.
     */
    public static ArrayList<String> showUnmarkedTask(Task task) {
        ArrayList<String> unmarkedTaskMessage = new ArrayList<>();
        unmarkedTaskMessage.add("OK! I've marked this task as not done yet:");
        unmarkedTaskMessage.add(task.toString());

        return unmarkedTaskMessage;
    }

    /**
     * Generates a message to indicate a task has been added to a task list and returns it as an ArrayList of strings.
     *
     * @param task The task to be added and included in the message.
     * @param taskList The task list to which the task is added.
     * @return An ArrayList containing the message indicating the added task and the updated task count.
     */
    public static ArrayList<String> showAddedTask(Task task, TaskList taskList) {
        ArrayList<String> addedTaskMessage = new ArrayList<>();
        addedTaskMessage.add("Got it! I've added this task:");
        addedTaskMessage.add(task.toString());
        addedTaskMessage.add(String.format("Now you have %d tasks in the list", taskList.getSize()));

        return addedTaskMessage;
    }

    /**
     * Generates a message to indicate a task has been deleted from a task list and returns it as an ArrayList of strings.
     *
     * @param task The task to be deleted and included in the message.
     * @param taskList The task list from which the task is removed.
     * @return An ArrayList containing the message indicating the deleted task and the updated task count.
     */
    public static ArrayList<String> showDeletedTask(Task task, TaskList taskList) {
        ArrayList<String> deletedTaskMessage = new ArrayList<>();
        deletedTaskMessage.add("Noted. I've removed this task:");
        deletedTaskMessage.add(task.toString());
        deletedTaskMessage.add(String.format("Now you have %d tasks in the list", taskList.getSize()));

        return deletedTaskMessage;
    }

    /**
     * Generates a message displaying the tasks in a task list and returns it as an ArrayList of strings.
     *
     * @param taskList The task list from which tasks are retrieved for display.
     * @return An ArrayList containing the message displaying the tasks in the list.
     */
    public static ArrayList<String> showList(TaskList taskList) {
        ArrayList<String> listMessage = new ArrayList<>();
        listMessage.add("Sure! Here are the tasks in your list");
        for (int i = 0; i < taskList.getSize(); i++) {
            String str = String.format("%d. %s", i + 1, taskList.getTask(i));
            listMessage.add(str);
        }

        return listMessage;
    }

    /**
     * Generates a message displaying the tasks that match a certain criteria from a task list and returns it as an ArrayList of strings.
     *
     * @param matchingTasks The task list containing matching tasks to be displayed.
     * @return An ArrayList containing the message displaying the matching tasks in the list.
     */
    public static ArrayList<String> showMatchingTasks(TaskList matchingTasks) {
        ArrayList<String> matchingTasksMessage = new ArrayList<>();
        matchingTasksMessage.add("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            String str = String.format("            %d.%s", i + 1, matchingTasks.getTask(i));
            matchingTasksMessage.add(str);
        }
        return matchingTasksMessage;
    }

    /**
     * Generates a farewell message for program exit and returns it as an ArrayList of strings.
     *
     * @return An ArrayList containing the farewell message for program exit.
     */
    public static ArrayList<String> showExit() {
        ArrayList<String> exitMessage = new ArrayList<>();
        exitMessage.add("Bye ~ Hope to see you again soon ~");

        return exitMessage;
    }
}
