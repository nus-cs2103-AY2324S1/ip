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
    public void startProgram() {
        System.out.println(MESSAGE_WELCOME);
        storage.load();
    }

    /**
     * Prints the program end message.
     */
    public void endProgram() {
        System.out.println(MESSAGE_GOODBYE);
    }
    
    /**
     * Prints the marked message and the String representation of the task.
     * 
     * @param task The task to be printed
     */
    public void printMarked(Task task) {
        System.out.println(MESSAGE_MARK);
        System.out.println(task);
    }

    /**
     * Prints the unmarked message and the String representation of the task.
     * 
     * @param task The task to be printed
     */
    public void printUnmarked(Task task) {
        System.out.println(MESSAGE_UNMARK);
        System.out.println(task);
    }

    /**
     * Prints the add message, the String representation of the task
     * and the size of the list.
     * 
     * @param task The task to be printed
     * @param size The size of the modified list.
     */
    public void addTask(Task task, int size) {
        System.out.println(MESSAGE_ADD);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the delete message, the String representation of the task
     * and the size of the list.
     * 
     *  @param task The task to be printed.
     *  @param size The size of the modified list.
     */
    public void deleteTask(Task task, int size) {
        System.out.println(MESSAGE_DELETE);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints every task to the user.
     * 
     * @param tasks The list of tasks.
     */
    public void printStorageList(List<Task> tasks) {
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ":" + task);
        }
    }

    /**
     * Prints the error message for incomplete commands.
     * 
     *  @param str The string to be printed.
     */
    public void incompleteCommand(String str) {
        System.out.println(str);
    }

     /**
     * Prints the error message for invalid inputs.
     */
    public void invalidInput() {
        System.out.println(MESSAGE_INVALID);
    }
}