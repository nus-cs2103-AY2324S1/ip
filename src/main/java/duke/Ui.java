package duke;

import duke.task.Task;
import duke.task.Tasklist;

/**
 * Represents a user interface handler for the Duke application.
 * Provides methods to print relevant messages to the console.
 */
public class Ui {

    /**
     * Name of the Bot.
     */
    private static final String NAME = "Bot";

    /**
     * Greeting message from the Bot.
     */
    private static final String GREETING = "Hello I'm " + NAME + "\nWhat can I do for you?";

    /**
     * Exit message from the Bot.
     */
    private static final String END = "Bye bye";

    // ... Other string constants ...

    /**
     * Prints greeting message to the console.
     */
    public static void greet() {
        System.out.println(GREETING);
    }
    /**
     * Prints the list of tasks.
     *
     * @param t Tasklist containing tasks.
     */
    public static void printList(Tasklist t) {
        System.out.println("Here are the tasks in your list:");
        t.printList();
    }

    /**
     * Notifies user of the application's exit.
     */
    public static void exit() {
        System.out.println(END);
    }

    /**
     * Notifies user that a task has been added and prints the current task count.
     *
     * @param t Task to be added.
     * @param size Current number of tasks after addition.
     */
    public static void add(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Notifies user that a task has been deleted and prints the current task count.
     *
     * @param t Task to be deleted.
     * @param size Current number of tasks after deletion.
     */
    public static void delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Notifies user that a task has been marked as done.
     *
     * @param t Task to be marked as done.
     */
    public static void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    /**
     * Notifies user that a task has been marked as not done.
     *
     * @param t Task to be unmarked.
     */
    public static void unmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

}
