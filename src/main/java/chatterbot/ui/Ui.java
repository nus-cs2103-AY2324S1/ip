package chatterbot.ui;

import chatterbot.data.Deadline;
import chatterbot.data.Event;
import chatterbot.data.Task;
import chatterbot.data.Todo;

import java.util.ArrayList;

/**
 * Decides what will be returned to the user.
 */
public class Ui {

    private static ArrayList<Task> list;

    public Ui(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints a welcome message for the user.
     */
    public static void showWelcomeMessage() {
        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints a goodbye message for the user.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the current task list for the user.
     */
    public static void showTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (Task t : list) {
            System.out.println((list.indexOf(t) + 1) + ". "
                    + t.toString());
        }
    }

    /**
     * Prints a success message to let the user know the task was marked successfully.
     * @param toMark This is the task that will be marked.
     */
    public static void showMarked(String toMark) {
        System.out.println("Nice! I've marked this task as done:\n" + "[X] "
                + list.get(Integer.parseInt(toMark) - 1).description);
    }

    /**
     * Prints a success message to let the user know the task was unmarked successfully.
     * @param toUnmark This is the task that will be unmarked.
     */
    public static void showUnmarked(String toUnmark) {
        System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] "
                + list.get(Integer.parseInt(toUnmark) - 1).description);
    }

    /**
     * Prints a success message to let the user know the deadline task was added successfully,
     * and the number of tasks they currently have.
     * @param d This is the description and deadline of the added deadline task.
     */
    public static void showAddedDeadline(Deadline d) {
        System.out.println("Got it. I've added this task:\n" + d.toString() + "\nNow you have " + list.size()
                + " tasks in the list.");
    }

    /**
     * Prints a success message to let the user know the todo task was added successfully,
     * and the number of tasks they currently have.
     * @param td This is the description of the added todo task.
     */
    public static void showAddedTodo(Todo td) {
        System.out.println("Got it. I've added this task:\n" + td.toString() + "\nNow you have " + list.size()
                + " tasks in the list.");
    }

    /**
     * Prints a success message to let the user know the event task was added successfully,
     * and the number of tasks they currently have.
     * @param e This is the description, start and end time of the added event task.
     */
    public static void showAddedEvent(Event e) {
        System.out.println("Got it. I've added this task:\n" + e.toString() + "\nNow you have " + list.size()
                + " tasks in the list.");
    }

    /**
     * Prints a success message to let the user know the task was deleted successfully.
     * @param userMessage This is the task in the user input that will be deleted.
     */
    public static void showDeleted(String userMessage) {
        System.out.println("Noted. I've removed this task:\n"
                + list.get((Integer.parseInt(userMessage.substring(7)))-1) + "\nNow you have "
                + (list.size() - 1) + " tasks in the list.");
    }

    /**
     * Prints a message to tell the user the entered input is unknown by ChatterBot.
     * @param userMessage This is the user input that is unknown.
     */
    public static void showUnknownCommand(String userMessage) {
        System.out.println("Unknown command: " + userMessage);
    }
}