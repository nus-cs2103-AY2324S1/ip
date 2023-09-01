package duke;

import java.util.Scanner;

/**
 * The Ui class represents the User Interface.
 */
public class Ui {

    /**
     * Prints the starting messages of the chatbot.
     */
    public static void start() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Hello! I'm ChatGP0");
        System.out.println("     " + "What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Returns the input received.
     *
     * @param scan The scanner opened to scan the input.
     */
    public static String getInput(Scanner scan) {
        String input = scan.nextLine();
        return input;
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks as a string.
     */
    public static void listOfTasks(String tasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Here are the tasks in your list:");
        System.out.println(tasks);
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the added task to the user.
     *
     * @param taskStr The new task as a string.
     * @param size The number of tasks.
     */
    public static void addTask(String taskStr, int size) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskStr);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the mistake the user made for the task description.
     *
     * @param type The type of task.
     */
    public static void emptyDesc(String type) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! The description of a " + type + " cannot be empty.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the mistake the user made for the deadline or duration.
     *
     * @param type The type of task.
     */
    public static void unclear(String type) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! The " + type + " is unclear.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the mistake the user made for the date and time format.
     */
    public static void wrongDateTimeFormat() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! Please follow the \"yyyy-MM-dd HHmm\" format.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the task being marked.
     *
     * @param task The task being marked.
     */
    public static void mark(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the task being unmarked.
     *
     * @param task The task being unmarked.
     */
    public static void unmark(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "OK, I've marked this task as not done yet:");
        System.out.println("       " + task.toString());
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the deleted task to the user.
     *
     * @param task The deleted task.
     * @param size The number of tasks left.
     */
    public static void delete(Task task, int size) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the mistake the user made for the task number not existing.
     */
    public static void invalidTask() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! This task does not exist :O");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the mistake the user made for the command not existing.
     */
    public static void invalidText() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints a goodbye statement before the program ends.
     */
    public static void bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
