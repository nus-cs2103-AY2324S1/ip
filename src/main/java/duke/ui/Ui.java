package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The Ui class handles user interface interactions in the Duke application.
 */
public class Ui {
    private static final String DIVIDER = "___________________________________\n";
    /**
     * Displays a farewell message to the user upon exiting the application.
     *
     * @return The farewell message.
     */
    public String showExit() {
        return "Bye";
    }
    /**
     * Displays the standard divider line.
     *
     * @return The divider message.
     */
    public String showDivider() {
        return DIVIDER;
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     * @return The error message.
     */
    public String showError(String error) {
        return "Uh oh! Duke has run into a problem:\n" + error;
    }

    /**
     * Displays a message confirming the deletion of a task and the updated task list size.
     *
     * @param task The task that was deleted.
     * @param list The updated list of tasks.
     * @return The confirmation message.
     */
    public String showDelete(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay, I have deleted this task from the list:\n\t %s\n"
                + "Now you have %d items in your list\n", task.toString(), list.size());
        return msg;
    }

    /**
     * Displays a message confirming the addition of a new task and the updated task list size.
     *
     * @param task The task that was added.
     * @param list The updated list of tasks.
     * @return The confirmation message.
     */
    public String showTaskAdded(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay, I have added a new %s:\n\t %s\n"
                + "You now have %d items in your list!", task.getType(), task.toString(), list.size());
        return msg;
    }

    /**
     * Displays a message confirming the marking or unmarking of a task as done.
     *
     * @param type The type of action ("mark" or "unmark").
     * @param task The task that was marked or unmarked.
     * @return The confirmation message.
     */
    public String showMark(String type, Task task) {
        String msg = String.format("Okay, I have unmarked this task:\n%s\n", task.toString());
        if (type.equals("mark")) {
            msg = String.format("Nice!! I have marked this task as done:\n%s\n", task.toString());
        }
        return msg;
    }

    /**
     * Displays the list of tasks with their respective indices.
     *
     * @param list The list of tasks to be displayed.
     * @return The list of tasks message.
     */
    public String showList(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        String msg = "Here are the tasks in your list:\n";
        for (int i = 0; i < temp.length; i++) {
            String nextLine = String.format("%d. %s\n", i + 1, temp[i].toString());
            msg = msg.concat(nextLine);
        }
        return msg;
    }

    /**
     * Displays a list of tasks that match the specified search criteria.
     *
     * This method is used to show a subset of tasks from the given list that match a certain search criteria.
     * It displays the matching tasks along with their indices in the list.
     *
     * @param list The list of tasks to search within.
     * @return The list of matching tasks message.
     */
    public String showListMatching(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        String msg = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < temp.length; i++) {
            String nextLine = String.format("%d. %s\n", i + 1, temp[i].toString());
            msg = msg.concat(nextLine);
        }
        return msg;
    }
    /**
     * Displays a help message to the user, listing supported commands.
     *
     * @return The help message.
     */
    public String showHelp() {
        String msg = "These are the commands that Duke supports:\n"
                + "1. todo (DESCRIPTION)\n"
                + "2. deadline (DESCRIPTION) /by DATE\n"
                + "3. event (DESCRIPTION) /from DATE /to DATE\n"
                + "4. mark/unmark/delete (POSITION)";
        return msg;
    }
    /**
     * Displays message to the user, informing them that the input is invalid.
     *
     * @return The invalid input message.
     */
    public String showInvalidInput() {
        String msg = "It looks like you have entered an invalid input!\n"
                + "For a list of all the valid inputs type \"help\"";
        return msg;
    }
}
