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
    public static String greet() {
        return GREETING;
    }
    /**
     * Prints the list of tasks.
     *
     * @param t Tasklist containing tasks.
     */
    public static String printList(Tasklist t) {
        System.out.println("Here are the tasks in your list:");
        t.printList();
        return "Here are the tasks in your list:\n" + t.toString();
    }

    /**
     * Notifies user of the application's exit.
     */
    public static String exit() {
        return END;
    }

    /**
     * Notifies user that a task has been added and prints the current task count.
     *
     * @param t Task to be added.
     * @param size Current number of tasks after addition.
     */
    public static String add(Task t, int size) {
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + size + " tasks in the list.";
    }
    /**
     * Notifies user that a task has been deleted and prints the current task count.
     *
     * @param t Task to be deleted.
     * @param size Current number of tasks after deletion.
     */
    public static String delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        return "Noted. I've removed this task:\n" + t.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Notifies user that a task has been marked as done.
     *
     * @param t Task to be marked as done.
     */
    public static String mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Notifies user that a task has been marked as not done.
     *
     * @param t Task to be unmarked.
     */
    public static String unmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    public static String find(String s) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(s);
        return "Here are the matching tasks in your list:\n" + s;
    }

}
