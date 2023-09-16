package ui;

import java.util.Scanner;

import list.TaskList;
import tasks.Task;


/**
 * The class that is used to show the user interface.
 */
public class Ui {

    private final Scanner scanner;
    private final String lineBreak = "\n________________________________________\n";

    /**
     * Constructs the method to initialise the UI.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and trims the spaces at both ends.
     *
     * @return A string of the input.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Returns a line.
     */
    public void showLine() {
        System.out.println(lineBreak);
    }
    /**
     * Returns out the error.
     *
     * @param e The error code produced.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Shows task being added.
     *
     * @param task The task to add.
     * @param userList The TaskList to add to.
     */
    public String showTaskAdded(Task task, TaskList userList) {
        String top = ("Got it. I've added this task: \n" + task);
        int size = userList.size();
        String bottom = ("Now you have " + size + " tasks in the list.");
        return top + "\n" + bottom;
    }

    /**
     * Greets the user at the start.
     */
    public String showGreetings() {
        String name = "CathyTheChattyCat";
        String top = (lineBreak + "Hello! I'm " + name);
        String bottom = ("What can I do for you?" + lineBreak);
        return top + "\n" + bottom;
    }

    /**
     * Shows that a task is marked.
     *
     * @param task The task being marked.
     */
    public String showMarkedTask(Task task) {
        return ("Nice! I've marked this task as done: \n\t" + task);
    }

    /**
     * Shows that a task is unmarked.
     *
     * @param task The task being unmarked.
     */
    public String showUnMarkedTask(Task task) {
        return ("OK, I've unmarked this task as not done yet: \n\t" + task);
    }

    /**
     * Shows that a task is deleted.
     *
     * @param task The task being deleted.
     * @param userList The userList that is deleted from.
     */
    public String showDeleteTask(Task task, TaskList userList) {
        String top = ("Noted. I've removed this task: \n" + task);
        String bottom = ("Now you have " + userList.size() + " tasks in the list");
        return top + "\n" + bottom;
    }

    /**
     * Shows that the task is cleared.
     */
    public String showClearTask() {
        return ("Noted. I've removed all the tasks.");
    }

    /**
     * Shows all the task in the list.
     *
     * @param userList The list the task is from.
     */
    public String showList(TaskList userList) {
        //System.out.println(lineBreak);
        StringBuilder builder = new StringBuilder();
        String top = ("Here are the tasks in your list:");
        for (int i = 0; i < userList.size(); i++) {
            int index = i + 1;
            builder.append(index).append(".").append(userList.get(i)).append('\n');
        }
        return top + "\n" + builder;
    }

    /**
     * Says BYE.
     */
    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Shows the result found base on the word given.
     *
     * @param foundList The list of found tasks.
     * @return A string of all the tasks.
     */
    public String showFoundResults(TaskList foundList) {
        StringBuilder builder = new StringBuilder();
        String top = ("Here are the matching tasks in your list:");
        for (int i = 0; i < foundList.size(); i++) {
            int index = i + 1;
            builder.append(index).append(".").append(foundList.get(i)).append("\n");
        }
        return top + "\n" + builder;
    }
}
