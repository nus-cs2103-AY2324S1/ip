package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the user interface for the chatbot.
 */
public class Ui {
    private static final String LINE = "\t____________________________________________________________";
    private Scanner input = new Scanner(System.in);

    /**
     * Reads the user input from the CLI.
     *
     * @return The user input;
     */
    public String readInput() {
        return this.input.nextLine();
    }

    /**
     * Shows user the welcome message when chatbot is started.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("\t Hello! I'm Bard.");
        System.out.println("\t What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user the exit message when chatbot is exited.
     */
    public void showExit() {
        this.input.close();
        System.out.println(LINE);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Shows user the list of tasks.
     *
     * @param taskList The list of tasks to show.
     */
    public void showList(TaskList taskList) {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("\t There are no tasks in your list.");
            System.out.println(LINE);
            System.out.println();
            return;
        }

        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskList.getLength(); i++) {
            System.out.println("\t " + (i + 1) + ". " + taskList.getTask(i));
        }

        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user a message when a task is added.
     *
     * @param task The added task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public void showAdd(Task task, int totalNumTask) {
        System.out.println(LINE);
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + task + "\n\t Now you have " + totalNumTask + " tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user a message when a task is deleted.
     *
     * @param task The deleted task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public void showDelete(Task task, int totalNumTask) {
        System.out.println(LINE);
        System.out.println("\t Noted. I've removed this task:\n" +
                "\t\t" + task +
                "\n\t Now you have " + totalNumTask + " tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user a message when a task is marked as done.
     *
     * @param task The task marked as done.
     */
    public void showDone(Task task) {
        System.out.println(LINE);
        System.out.println("\t Nice! I've marked this task as done:\n" +
                "\t\t" + task);
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user a message when a task is marked as not done.
     *
     * @param task The task marked as not done.
     */
    public void showNotDone(Task task) {
        System.out.println(LINE);
        System.out.println("\t OK, I've marked this task as NOT done yet:\n" +
                "\t\t" + task);
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user the tasks for a specific date.
     *
     * @param date The date for which the tasks are displayed.
     * @param taskList The list of tasks to search.
     */
    public void showTasksOnDate(LocalDate date, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("\t Tasks on " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":");

        boolean foundTasks = false;

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateTime().toLocalDate().equals(date)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDateTime().toLocalDate().equals(date)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            }
        }

        if (!foundTasks) {
            System.out.println("\t\t Yay! You have no tasks on "
                    + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " :D");
        }

        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user the tasks for today.
     *
     * @param today The current date.
     * @param taskList The list of tasks to search.
     */
    public void showTasksForToday(LocalDate today, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("\t Tasks for today (" + today.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "):");

        boolean foundTasks = false;

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateTime().toLocalDate().equals(today)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDateTime().toLocalDate().equals(today)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            }
        }

        if (!foundTasks) {
            System.out.println("\t\t Yay! You have no tasks today :D");
        }

        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user an error message for Duke exceptions.
     *
     * @param e The Duke exception to display.
     */
    public void showDukeException(DukeException e) {
        System.out.println(LINE);
        System.out.println("\t" + e.getMessage());
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user an error message for invalid date-time format.
     */
    public void showInvalidDateTimeFormat() {
        System.out.println(LINE);
        System.out.println("\tPlease enter the time in the format of <d/M/yyyy HHmm>!");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user an error message for invalid date format.
     */
    public void showInvalidDateFormat() {
        System.out.println(LINE);
        System.out.println("\tPlease enter the date in the format of <d/M/yyyy>!");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user an error message for saving tasks to data file.
     */
    public void showSavingError() {
        System.out.println(LINE);
        System.out.println("\tError saving tasks to the data file.");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Shows user an error message for array index out of bounds.
     */
    public void showArrayIndexOutOfBoundsException() {
        System.out.println(LINE);
        System.out.println("\t ☹ OOPS!!! Please provide a valid task number.");
        System.out.println(LINE);
        System.out.println();
    }
}
