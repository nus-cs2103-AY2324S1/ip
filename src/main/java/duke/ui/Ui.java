package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.CommandDetailException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents the user interface for the Duke application.
 * The user interface is responsible for displaying information to the user.
 */
public class Ui {

    private static final String NAME = "Kevin";
    private static final int SPLITTER_LENGTH = 50;
    private final Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Displays a line to separate different sections of output.
     */
    public void showLine() {
        for (int i = 0; i < Ui.SPLITTER_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?\n");
    }

    public String showWelcomeGui() {
        return "Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?\n";
    }


    public void showLoadingError() {
        System.out.println("OOPS!!! There was an error loading the file.");
    }

    public String showLoadingErrorGui() {
        return "OOPS!!! There was an error loading the file.";
    }

    public void showCommandNotRecognized() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String showCommandNotRecognizedGui() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public void showNoCommandDetail() {
        System.out.println("OOPS!!! There is something wrong with the command.");
    }

    public String showNoCommandDetailGui() {
        return "OOPS!!! There is something wrong with the command.";
    }

    public void showTimeParsingError() {
        System.out.println("OOPS!!! Please enter the date and Duke.time in the format: yyyy-mm-dd");
    }

    public String showTimeParsingErrorGui() {
        return "OOPS!!! Please enter the date and Duke.time in the format: yyyy-mm-dd";
    }

    /**
     * Displays the exit message to the user.
     */
    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this Duke.task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public String showAddTaskGui(TaskList tasks, Task task) {
        return "Got it. I've added this Duke.task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays the exit message to the user.
     */
    public void showDelete(TaskList tasks, int index) throws CommandDetailException {
        System.out.println("Noted. I've removed this Duke.task:");
        System.out.println(tasks.getTask(index));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays the exit message to the user.
     */
    public String showDeleteGui(TaskList tasks, int index) throws CommandDetailException {
        return "Noted. I've removed this Duke.task:\n" + tasks.getTask(index) + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Displays the exit message to the user.
     */
    public void showMark(TaskList tasks, int index) throws CommandDetailException {
        System.out.println("Nice! I've marked this Duke.task as done:");
        System.out.println(tasks.getTask(index));
    }

    public String showMarkGui(TaskList tasks, int index) throws CommandDetailException {
        return "Nice! I've marked this Duke.task as done:\n" + tasks.getTask(index);
    }

    /**
     * Displays the exit message to the user.
     */
    public void showUnmark(TaskList tasks, int index) throws CommandDetailException {
        System.out.println("Nice! I've marked this Duke.task as undone:");
        System.out.println(tasks.getTask(index));
    }

    public String showUnmarkGui(TaskList tasks, int index) throws CommandDetailException {
        return "Nice! I've marked this Duke.task as undone:\n" + tasks.getTask(index);
    }

    /**
     * Displays the exit message to the user.
     */
    public void showList(TaskList tasks) throws CommandDetailException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays the exit message to the user.
     */
    public String showListGui(TaskList tasks) throws CommandDetailException {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Displays the exit message to the user.
     */
    public void showFind(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("There is no matching task in your list.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays the exit message to the user.
     */
    public String showFindGui(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        if (tasks.size() == 0) {
            result.append("There is no matching task in your list.");
            return result.toString();
        }
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    public void showClear() {
        System.out.println("Noted. I've cleared all the tasks in your list.");
    }

    public String showClearGui() {
        return "Noted. I've cleared all the tasks in your list.";
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String showExitGui() {
        return "Bye. Hope to see you again soon!";
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public String showErrorGui(Exception e) {
        return e.getMessage();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }
}
