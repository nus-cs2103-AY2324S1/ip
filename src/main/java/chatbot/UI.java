package chatbot;

import java.util.List;

/**
 * Handles the printing of statements to the user.
 */
public class UI {
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_WELCOME = "Hello! I'm Afro\n" + "What can I do for you?\n";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_MARK = "OK, I've marked this task as done:";
    private static final String MESSAGE_ADD = "Got it. I've added this task:";
    private static final String MESSAGE_DELETE = "Noted. I have removed this task.";
    private static final String MESSAGE_INVALID = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /** Storage to load and write files. */
    private Storage storage;

    /**
     * Constructor for UI. Initialises the storage.
     */
    public UI(Storage storage) {
        this.storage = storage;
    }

    /**
     * Prints the greeting message and loads a previous text file if any.
     */
    public String startProgram() {
        // System.out.println(MESSAGE_WELCOME);
        String output = storage.load() + "\n" + MESSAGE_WELCOME;
        return MESSAGE_WELCOME;
    }

    /**
     * Prints the program end message.
     */
    public String endProgram() {
        return MESSAGE_GOODBYE;
    }
    
    /**
     * Prints the marked message and the String representation of the task.
     * 
     * @param task The task to be printed
     */
    public String printMarked(Task task) {
        // System.out.println(MESSAGE_MARK);
        // System.out.println(task);
        assert task.isDone : "This task is still marked as not completed.";
        String toBePrinted = MESSAGE_MARK + "\n" + task;
        return toBePrinted;
    }

    /**
     * Prints the unmarked message and the String representation of the task.
     * 
     * @param task The task to be printed
     */
    public String printUnmarked(Task task) {
        // System.out.println(MESSAGE_UNMARK);
        // System.out.println(task);
        assert !task.isDone : "This task is still marked as completed.";
        String toBePrinted = MESSAGE_UNMARK + "\n" + task;
        return toBePrinted;
    }

    /**
     * Prints the add message, the String representation of the task
     * and the size of the list.
     * 
     * @param task The task to be printed
     * @param size The size of the modified list.
     */
    public String addTask(Task task, int size) {
        // System.out.println(MESSAGE_ADD);
        // System.out.println(task);
        // System.out.println("Now you have " + size + " tasks in the list.");
        String toBePrinted = MESSAGE_ADD + "\n" + task + "\n"
                                + "Now you have " + size + " tasks in the list.";
        return toBePrinted;
    }

    /**
     * Prints the deleted message, the String representation of the task
     * and the size of the list.
     * 
     *  @param task The task to be printed.
     *  @param size The size of the modified list.
     */
    public String deleteTask(Task task, int size) {
        // System.out.println(MESSAGE_DELETE);
        // System.out.println(task);
        // System.out.println("Now you have " + size + " tasks in the list.");
        String toBePrinted = MESSAGE_DELETE + "\n" + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        return toBePrinted;
    }

    /**
     * Prints every task to the user.
     * 
     * @param tasks The list of tasks.
     */
    public String printStorageList(List<Task> tasks) {
        String toBePrinted = "";
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            // System.out.println(index + ":" + task);
            toBePrinted += index + ":" + task + "\n";
        }
        return toBePrinted;
    }

    /**
     * Prints the error message for incomplete commands.
     * 
     *  @param str The string to be printed.
     */
    public String printIncompleteCommand(String str) {
        // System.out.println(str);
        return str;
    }

     /**
     * Prints the error message for invalid inputs.
     */
    public String invalidInput() {
        // System.out.println(MESSAGE_INVALID);
        return MESSAGE_INVALID;
    }
}