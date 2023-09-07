package duke;

import java.util.Scanner;

/**
 * The Ui class represents the User Interface.
 */
public class Ui {
    private static final String line = "    __________________________________________________________";

    /**
     * Prints the starting messages of the chatbot.
     */
    public static String start() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "Hello! I'm ChatGP0" + "\n");
        sb.append("     " + "What can I do for you?" + "\n");
        sb.append(line + "\n");
        return sb.toString();
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
    public static String listOfTasks(String tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "Here are the tasks in your list:" + "\n");
        sb.append(tasks + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the added task to the user.
     *
     * @param taskStr The new task as a string.
     * @param size The number of tasks.
     */
    public static String addTask(String taskStr, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     Got it. I've added this task:" + "\n");
        sb.append("       " + taskStr + "\n");
        sb.append("     Now you have " + size + " tasks in the list." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the mistake the user made for the task description.
     *
     * @param type The type of task.
     */
    public static String emptyDesc(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! The description of a " + type + " cannot be empty." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the mistake the user made for the deadline or duration.
     *
     * @param type The type of task.
     */
    public static String unclear(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! The " + type + " is unclear." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the mistake the user made for the date and time format.
     */
    public static String wrongDateTimeFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! Please follow the \"yyyy-MM-dd HHmm\" format." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the task being marked.
     *
     * @param task The task being marked.
     */
    public static String mark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "Nice! I've marked this task as done:" + "\n");
        sb.append("       " + task.toString() + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the task being unmarked.
     *
     * @param task The task being unmarked.
     */
    public static String unmark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "OK, I've marked this task as not done yet:" + "\n");
        sb.append("       " + task.toString() + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the deleted task to the user.
     *
     * @param task The deleted task.
     * @param size The number of tasks left.
     */
    public static String delete(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     Noted. I've removed this task:" + "\n");
        sb.append("       " + task.toString() + "\n");
        sb.append("     Now you have " + size + " tasks in the list." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the mistake the user made for the task number not existing.
     */
    public static String invalidTask() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! This task does not exist :O" + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints the mistake the user made for the command not existing.
     */
    public static String invalidText() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Prints a goodbye statement before the program ends.
     */
    public static String bye() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "Bye. Hope to see you again soon!" + "\n");
        sb.append(line);
        return sb.toString();
    }

    /**
     * Prints list of tasks that matches the search input.
     *
     * @param selectedTasks List of tasks.
     */
    public static String searchTasks(String selectedTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     Here are the matching tasks in your list:" + "\n");
        sb.append(selectedTasks + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }
}
