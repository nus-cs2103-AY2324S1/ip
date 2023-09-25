package main;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * UI class - User Interface - Handles the display shown by the program.
 */
public class UI {

    /**
     * Constructor for UI class.
     */
    public UI() {}
    /**
     * Generates the divider displayed in the terminal.
     */
    String printDivider() {
        String line = "\n________________________________________________________";
        return line;
    }

    /**
     * Generates the welcome message displayed in the terminal.
     */
    public String printWelcomeMessage() {

        StringBuilder sb = new StringBuilder();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        sb.append("Hello from\n" + logo);

        sb.append(this.printDivider());

        sb.append("\nHello! I'm DUKE");
        sb.append("\nWhat can I do for you?");

        sb.append(this.printDivider());
        return String.valueOf(sb);
    }

    /**
     * Lists out the tasks stored in the ArrayList<Task> Object.
     *
     * @param taskArrayList Contains the list of Tasks.
     * @return List all tasks in String format
     */
    public String printList(ArrayList<Task> taskArrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.printDivider());
        if (taskArrayList.size() == 0) {
            sb.append("\nThere are no tasks in your list.");
        } else {
            sb.append("\nHere are the tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                int index = i + 1;
                Task t = taskArrayList.get(i);
                sb.append("\n" + index + "." + t.toString());
            }
        }
        sb.append(this.printDivider());
        return String.valueOf(sb);
    }

    /**
     * Finds out the tasks stored in the ArrayList<Task> Object.
     *
     * @param taskArrayList Contains the list of Tasks.
     * @param isMatchingList Boolean for whether it is a list of tasks queried by the user.
     * @return List of tasks.
     */
    public String findList(ArrayList<Task> taskArrayList, Boolean isMatchingList) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.printDivider());
        if (taskArrayList.size() == 0) {
            sb.append("\nThere are no matching tasks in your list.");
        } else if (isMatchingList) {
            sb.append("\nHere are the " + taskArrayList.size() + " matching tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                int index = i + 1;
                Task t = taskArrayList.get(i);
                sb.append("\n").append(index + "." + t.toString());
            }
        } else {
            sb.append("\nHere are the tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                int index = i + 1;
                Task t = taskArrayList.get(i);
                sb.append("\n").append(index + "." + t.toString());
            }
        }
        sb.append(this.printDivider());
        return String.valueOf(sb);
    }

    /**
     * Generates the bye message displayed in the terminal.
     *
     * @return Bye message in string format.
     */
    public String printByeMessage() {

        StringBuilder sb = new StringBuilder();
        sb.append("\nBye. Hope to see you again soon!");
        sb.append(this.printDivider());
        return String.valueOf(sb);
    }

    /**
     * Generates the delete message displayed in the terminal.
     *
     * @param input User input.
     * @param taskList Task list containing all the tasks.
     * @return Delete message in string format.
     */
    public String showDelete(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:");
        sb.append("\n").append(taskList.getTaskArrayList().get(taskIndex));
        return String.valueOf(sb);
    }

    /**
     * Generates the add task message displayed in the terminal.
     *
     * @param task Task to be added.
     * @return Add Task message in string format.
     */
    public static String showAddTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nGot it. I've added this task:");
        sb.append("\n").append(task);
        return String.valueOf(sb);
    }

    /**
     * Generates the mark task message displayed in the terminal.
     *
     * @param task Task to be marked.
     * @return Mark task message in string format.
     */
    public static String showMarked(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNice! I've marked this task as done:");
        sb.append("\n").append(task);
        return String.valueOf(sb);
    }

    /**
     * Generates the unmark task message displayed in the terminal.
     *
     * @param task Task to be unmarked.
     * @return Unmark task message in string format.
     */
    public static String showUnmarked(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nOK, I've marked this task as not done yet:");
        sb.append("\n").append(task);
        return String.valueOf(sb);
    }

    /**
     * Generates the update task description message displayed in the terminal.
     *
     * @param taskIndex Index of the task in the task list to be updated.
     * @param oldDescription Old description of the task to be updated.
     * @param newDescription New description of the task to be updated.
     * @return Update description message in string format.
     */
    public static String showUpdatedDescription(int taskIndex, String oldDescription, String newDescription) {
        StringBuilder sb = new StringBuilder();

        sb.append("Updated the description of Task ").append(taskIndex);
        sb.append(" from \"").append(oldDescription).append("\"");
        sb.append(" to \"").append(newDescription).append("\"");

        return String.valueOf(sb);
    }
}
