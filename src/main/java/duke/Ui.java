package duke;

import java.util.Scanner;

/**
 * Represents the class which handles user interactions and the messages
 * to be displayed to the user.
 */
public class Ui {
    private Scanner scanner;
    private final String LINE = "------------------------------------";
    private final String CHATBOT = "chuababyy";
    private final String COMMANDS =
            LINE + "\n" + "List of commands\n" + "1. todo [description]\n"
                    + "2. deadline [description] /by [deadline in DD-MM-YYYY TIME]\n"
                    + "3. event [description] /from [start date in DD-MM-YYYY TIME] " +
                    "/to [end date in DD-MM-YYYY TIME]\n"
                    + "4. mark [item_number]\n"
                    + "5. unmark [item_number]\n"
                    + "6. delete [item_number]\n"
                    + "7. list\n"
                    + "8. bye\n"
                    + LINE ;

    /**
     * Constructs a Ui instance and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a separator line.
     */
    public void showline() {
        System.out.println(LINE);
    }

    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void showWelcome() {
        showline();
        System.out.println("Hello! I'm " + CHATBOT);
        System.out.println("What can I do for you?");
        showline();
    }

    /**
     * Displays a message which indicates that the item to be added cannot be empty.
     */
    public void showEmptyMessage() {
        showline();
        System.out.println("Item to be added cannot be empty");
        showline();
    }

    /**
     * Displays a message which indicates an invalid command.
     */
    public void showUnknownMessage() {
        showline();
        System.out.println("Invalid structure. Please follow the valid commands below.\n" + COMMANDS);
        showline();
    }

    /**
     * Displays a message which indicates an invalid command.
     */
    public void showInvalidMessage() {
        System.out.println("Invalid structure. Please follow the valid commands below.\n" + COMMANDS);
    }

    /**
     * Displays a message which indicated the item does not exist.
     */
    public void showNoItemMessage() {
        showline();
        System.out.println("No such item exists");
        showline();
    }
    /**
     * Displays a goodbye message.
     */
    public void showByeMessage() {
        showline();
        System.out.println("Bye. Hope to see you again soon!");
        showline();
    }

    /**
     * Displays a TaskList to indicate what are the current items in the list.
     *
     * @param fullList The list to be displayed.
     */
    public void showList(TaskList fullList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(fullList.toString());
    }

    /**
     * Displays a success message when the user successfully adds a task.
     *
     * @param task The task to be added.
     * @param size The size of the current list.
     */
    public void showAddMessage(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    };

    /**
     * Displays a success message when the user successfully deletes a task.
     *
     * @param task The task to be deleted.
     * @param size The size of the current list.
     */
    public void showDeleteMessage(Task task, int size) {
        showline();
        System.out.println("Noted. I've removed this task:\n" +
                task.toString() + "\n" +
                        "Now you have " + size + " tasks in the list.");
        showline();
    }

    /**
     * Displays a success message when the user successfully marks a task.
     *
     * @param task The task to be marked.
     */
    public void showMarkMessage(Task task) {
        showline();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showline();
    }

    /**
     * Displays a success message when the user successfully unMarks a task.
     *
     * @param task The task to be unMarked.
     */
    public void showUnmarkMessage(Task task) {
        showline();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task.toString());
        showline();
    }

    /**
     * Displays an error message when the date format is invalid.
     */
    public void showInvalidDate() {
        showline();
        System.out.println("Invalid date format. Start date is after end date");
        showline();
    }
}