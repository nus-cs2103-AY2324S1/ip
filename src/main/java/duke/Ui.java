package duke;

import java.util.Scanner;

/**
 * The Ui class represents the User Interface.
 */
public class Ui {
    private static final String line = "    __________________________________________________________";

    /**
     * Returns the starting messages of the chatbot.
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
     * @return Human text input.
     */
    public static String getInput(Scanner scan) {
        String input = scan.nextLine();
        return input;
    }

    /**
     * Returns the list of tasks to the user.
     *
     * @param tasks The list of tasks as a string.
     * @return List of tasks.
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
     * Returns the added task to the user.
     *
     * @param taskStr The new task as a string.
     * @param size The number of tasks.
     * @return Added task as a string.
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
     * Returns the mistake the user made for the task description.
     *
     * @param type The type of task.
     * @return Empty description warning message.
     */
    public static String emptyDesc(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! The description of a " + type + " cannot be empty." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Returns the mistake the user made for the deadline or duration.
     *
     * @param type The type of task.
     * @return Unclear deadline or duration warning message.
     */
    public static String unclear(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! The " + type + " is unclear." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Returns the mistake the user made for the date and time format.
     *
     * @return Warning message for wrong date and time format.
     */
    public static String wrongDateTimeFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! Please follow the \"yyyy-MM-dd HHmm\" format." + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Returns the task being marked.
     *
     * @param task The task being marked.
     * @return Task being marked as a string.
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
     * Returns the task being unmarked.
     *
     * @param task The task being unmarked.
     * @return Task being unmarked as a string.
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
     * Returns the deleted task to the user.
     *
     * @param task The deleted task.
     * @param size The number of tasks left.
     * @return Task being deleted as a string.
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
     * Returns the mistake the user made for the task number not existing.
     *
     * @return Warning message for an invalid task number.
     */
    public static String invalidTask() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! This task does not exist :O" + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Returns the mistake the user made for the command not existing.
     *
     * @return Warning message for an invalid command.
     */
    public static String invalidText() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n");
        sb.append(line + "\n");
        return sb.toString();
    }

    /**
     * Returns a goodbye statement.
     *
     * @return Goodbye statement as a string.
     */
    public static String bye() {
        StringBuilder sb = new StringBuilder();
        sb.append(line + "\n");
        sb.append("     " + "Bye. Hope to see you again soon!" + "\n");
        sb.append(line);
        return sb.toString();
    }

    /**
     * Returns list of tasks that matches the search input.
     *
     * @param selectedTasks List of tasks.
     * @return List of tasks that match the search input.
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
