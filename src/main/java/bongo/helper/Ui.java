package bongo.helper;

import bongo.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner inputScanner;

    /**
     * A constructor for a Ui.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Returns the current line of the user's input.
     *
     * @return Current line of user input.
     */
    public String readCommand() {
        return this.inputScanner.nextLine();
    }

    /**
     * Adds lines to top and bottom of message.
     *
     * @param message User message.
     * @return A message sandwiched between two lines.
     */
    private String insertLines(String message) {
        return "____________________________________________________________\n" +
                message +
                "____________________________________________________________";
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        String welcomeMessage = " Hello! I'm Bongo!\n" +
                " What can I do for you?\n";
        System.out.println(insertLines(welcomeMessage));
    }

    /**
     * Shows goodbye message.
     */
    public void showGoodbye() {
        String goodbyeMessage = " Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        this.inputScanner.close();
    }

    /**
     * Shows all tasks to the user.
     *
     * @param tasks All user tasks.
     */
    public void showAllTasks(ArrayList<Task> tasks) {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        String tasksList = " Here are the tasks in your list:\n" +
                allTasks;
        System.out.println(tasksList.trim());
    }

    /**
     * Shows user the recently added task.
     *
     * @param newTask    New Task to be added.
     * @param totalTasks Number of total tasks.
     */
    public void showAddedTask(Task newTask, int totalTasks) {
        String addedTaskMessage = " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.", totalTasks);
        System.out.println(addedTaskMessage);
    }

    /**
     * Shows user the task that was recently marked done.
     *
     * @param task Recently marked done task.
     */
    public void showTaskIsDone(Task task) {
        String taskStatusMessage = " Nice! I've marked this task as done:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    /**
     * Shows user the task that was recently marked undone.
     *
     * @param task Recently marked undone task.
     */
    public void showTaskIsUndone(Task task) {
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    /**
     * Shows user the recently deleted task.
     *
     * @param task      Task that was recently deleted.
     * @param tasksLeft Number of tasks left.
     */
    public void showDeleteTask(Task task, int tasksLeft) {
        String taskDeleteMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", task) +
                String.format(" Now you have %d tasks in the list.", tasksLeft);
        System.out.println(taskDeleteMessage);
    }

    /**
     * Shows user an error message.
     *
     * @param errorMessage Error message that BongoException throws.
     */
    public void showError(String errorMessage) {
        String finalErrorMessage = " Oh no! Bongo ran into an error :(\n" + String.format(" %s", errorMessage);
        System.out.println(finalErrorMessage);
    }

    /**
     * Shows user the loading files error message.
     */
    public void showLoadingError() {
        String loadingErrorMessage = "Oh no! Bongo couldn't find the files, so the following files were created:";
        System.out.println(loadingErrorMessage);
    }
}
