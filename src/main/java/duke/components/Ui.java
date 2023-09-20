package duke.components;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the class which handles user interactions and the messages
 * to be displayed to the user.
 */
public class Ui {
    private Scanner scanner;
    private final String chatBot = "chuababyy";
    private final String commands =
            "List of commands\n" + "1. todo [description]\n"
                    + "2. deadline [description] /by [deadline in DD-MM-YYYY TIME]\n"
                    + "3. event [description] /from [start date in DD-MM-YYYY TIME] "
                    + "/to [end date in DD-MM-YYYY TIME]\n"
                    + "4. mark [item_number]\n"
                    + "5. unmark [item_number]\n"
                    + "6. delete [item_number]\n"
                    + "7. mass [delete/mark/unmark] [item_numbers]\n"
                    + "8. list\n"
                    + "9. bye";

    /**
     * Constructs a Ui instance and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when the chatBot starts.
     */
    public String showWelcome() {
        return "Hello! I'm " + chatBot + '\n' + "What can I do for you?";
    }

    /**
     * Displays a message which indicates that the item to be added cannot be empty.
     */
    public String showEmptyMessage() {
        return "Item to be added cannot be empty";
    }

    /**
     * Displays a message which indicates an invalid command.
     */
    public String showUnknownMessage() {
        return "Invalid structure. Please follow the valid commands below.\n" + commands;
    }

    /**
     * Displays a message which indicates an invalid command.
     */
    public String showInvalidMessage() {
        return "Invalid structure. Please follow the valid commands below.\n" + commands;
    }

    /**
     * Displays a message which indicated the item does not exist.
     */
    public String showNoItemMessage(int i) {
        return  "No such item exists: " + i;
    }

    /**
     * Displays a goodbye message.
     */
    public String showByeMessage() {
        return  "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a TaskList to indicate what are the current items in the list.
     *
     * @param fullList The list to be displayed.
     */
    public String showList(TaskList fullList) {
        return "Here are the tasks in your list:" + '\n'
                + fullList.toString();
    }

    /**
     * Displays a success message when the user successfully adds a task.
     *
     * @param task The task to be added.
     * @param size The size of the current list.
     */

    public String showAddMessage(Task task, int size) {
        return  "Got it. I've added this task:" + '\n' + task.toString() + '\n'
                        + "Now you have " + size + " tasks in the list.";
    };

    /**
     * Displays a success message when the user successfully deletes a task.
     *
     * @param task The task to be deleted.
     * @param size The size of the current list.
     */

    public String showDeleteMessage(Task task, int size) {
        return  "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a success message when the user successfully marks a task.
     *
     * @param task The task to be marked.
     */
    public String showMarkMessage(Task task) {
        return  "Nice! I've marked this task as done:" + '\n' + task.toString();
    }

    /**
     * Displays a success message when the user successfully unMarks a task.
     *
     * @param task The task to be unMarked.
     */
    public String showUnmarkMessage(Task task) {
        return  "Ok, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Displays an error message when the date format is invalid.
     */
    public String showInvalidDate() {
        return  "Invalid date format. Start date is after end date";
    }

    /**
     * Displays all the tasks which have description that match a keyword.
     *
     * @param list The list of tasks which match.
     */
    public String showFind(TaskList list) {
        return  "Here are the matching tasks in your list" + '\n'
                + list.toString();
    }

    /**
     * Displays message when there are no tasks that match a given keyword.
     */
    public String showNoFind() {
        return  "There are no matching tasks in your current list";
    }

    public String showCustomError(String error) {
        return  error;
    }
}
