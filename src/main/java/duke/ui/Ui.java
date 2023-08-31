package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.data.TaskList;
import duke.data.task.Task;

public class Ui {
    private final Scanner scanner;

    /** Constructor to initialize Ui */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Displays welcome message */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Dommi");
        System.out.println("What can I do for you?");
        showLine();
    }

    /** Displays goodbye message */
    public void showBye() {
        System.out.println("Goodbye! Hope to see you again soon! :D");
        showLine();
    }

    /**
     * Reads user input and return.
     * @return the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays a line */
    public void showLine() {
        System.out.println("_".repeat(60));
    }

    /** Displays loading error */
    public void showLoadingError() {
        System.out.println(".txt file not found! Creating .txt file ...");
    }

    /**
     * Displays error provided by the program.
     *
     * @param error the error message being thrown.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays task as marked.
     *
     * @param task the task that was marked.
     */
    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays task as unmarked.
     *
     * @param task the task that was unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays task as added.
     *
     * @param task the task that was added
     * @param noTasks number of tasks in arraylist.
     */
    public void showTaskAdded(Task task, int noTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    /**
     * Displays task as deleted.
     *
     * @param task the task that was deleted
     * @param noTasks number of tasks in arraylist.
     */
    public void showTaskDeleted(Task task, int noTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    /**
     * Displays all tasks in taskList
     *
     * @param taskList taskList from Duke.
     */
    public void showTaskList(TaskList taskList) {
        if (!taskList.hasTasks()) {
            System.out.println("No tasks have been created.");
        } else {
            for (int i = 0; i < taskList.countTasks(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i));
            }
        }
    }

    /** Displays invalid format error */
    public void showInvalidFormat() {
        System.out.println("☹ OOPS!!! Date has to be in yyyy-mm-dd format!");
    }

    /** Displays writing to file error */
    public void showWriteFileError() {
        System.out.println("☹ OOPS!!! Error writing to file!");
    }

    /** Displays invalid command error */
    public void showInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showSearchResults(ArrayList<Task> results) {
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + "." + results.get(i));
        }
    }
}