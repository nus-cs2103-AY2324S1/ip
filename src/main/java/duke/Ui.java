package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface for the chatbot.
 */
public class Ui {
    private Scanner input = new Scanner(System.in);

    private StringBuilder messages;

    public Ui() {
        messages = new StringBuilder();
    }

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
    public String showWelcome() {
        String message = "Hello! I'm Bard.\nWhat can I do for you?";
        display(message);
        return message;
    }

    /**
     * Shows user the exit message when chatbot is exited.
     */
    public String showExit() {
        this.input.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows user the list of tasks.
     *
     * @param taskList The list of tasks to show.
     */
    public String showList(TaskList taskList) {
        StringBuilder message = new StringBuilder();
        if (taskList.isEmpty()) {
            message.append("There are no tasks in your list.");
        } else {
            message.append("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getLength(); i++) {
                message.append("\n").append(i + 1).append(". ").append(taskList.getTask(i));
            }
        }
        display(message.toString());
        return message.toString();
    }

    /**
     * Shows user a message when a task is added.
     *
     * @param task The added task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public String showAdd(Task task, int totalNumTask) {
        return "Got it. I've added this task:\n"
                + task + "\nNow you have " + totalNumTask + " tasks in the list.";
    }

    /**
     * Shows user a message when a task is deleted.
     *
     * @param task The deleted task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public String showDelete(Task task, int totalNumTask) {
        return "Noted. I've removed this task:\n"
                + task + "\nNow you have " + totalNumTask + " tasks in the list.";
    }

    /**
     * Shows user a message when a task is marked as done.
     *
     * @param task The task marked as done.
     */
    public String showDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Shows user a message when a task is marked as not done.
     *
     * @param task The task marked as not done.
     */
    public String showNotDone(Task task) {
        return "OK, I've marked this task as NOT done yet:\n" + task;
    }

    /**
     * Shows user the tasks for a specific date.
     *
     * @param date The date for which the tasks are displayed.
     * @param matchingTasks An ArrayList of tasks that match the search criteria.
     */
    public String showTasksOnDate(LocalDate date, ArrayList<Task> matchingTasks) {
        StringBuilder message = new StringBuilder();
        message.append("Tasks on ").append(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))).append(":");
        boolean foundTasks = !matchingTasks.isEmpty();
        int taskNumber = 1;

        if (foundTasks) {
            for (Task task : matchingTasks) {
                message.append("\n").append(taskNumber).append(". ").append(task);
                taskNumber++;
            }
        } else {
            message.append("\nYay! You have no tasks on ")
                    .append(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))).append(" :D");
        }

        display(message.toString());
        return message.toString();
    }

    /**
     * Shows user the tasks for today.
     *
     * @param today The current date.
     * @param matchingTasks An ArrayList of tasks that match the search criteria.
     */
    public String showTasksForToday(LocalDate today, ArrayList<Task> matchingTasks) {
        StringBuilder message = new StringBuilder();
        message.append("Tasks for today (")
                .append(today.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))).append("):");
        boolean foundTasks = !matchingTasks.isEmpty();
        int taskNumber = 1;

        if (foundTasks) {
            for (Task task : matchingTasks) {
                message.append("\n").append(taskNumber).append(". ").append(task);
                taskNumber++;
            }
        } else {
            message.append("\nYay! You have no tasks today :D");
        }

        display(message.toString());
        return message.toString();
    }

    /**
     * Shows user the list of matching tasks.
     *
     * @param matchingTasks An ArrayList of tasks that match the search criteria.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder message = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            message.append("There are no matching tasks in your list.");
        } else {
            message.append("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append("\n").append(i + 1).append(". ").append(matchingTasks.get(i));
            }
        }
        display(message.toString());
        return message.toString();
    }

    /**
     * Shows user an error message for Duke exceptions.
     *
     * @param e The Duke exception to display.
     */
    public String showDukeException(DukeException e) {
        String message = e.getMessage();
        display(message);
        return message;
    }

    /**
     * Shows user an error message for invalid date-time format.
     */
    public String showInvalidDateTimeFormat() {
        String message = "Please enter the time in the format of <d/M/yyyy HHmm>!";
        display(message);
        return message;
    }

    /**
     * Shows user an error message for invalid date format.
     */
    public String showInvalidDateFormat() {
        String message = "Please enter the date in the format of <d/M/yyyy>!";
        display(message);
        return message;
    }

    /**
     * Shows user an error message for saving tasks to data file.
     */
    public String showSavingError() {
        String message = "Error saving tasks to the data file.";
        display(message);
        return message;
    }

    /**
     * Shows user an error message for array index out of bounds.
     */
    public String showArrayIndexOutOfBoundsException() {
        String message = "☹ OOPS!!! Please provide a valid task number.";
        display(message);
        return message;
    }

    // New method to display messages
    private void display(String message) {
        messages.append(message).append("\n"); // Append the message to the StringBuilder
    }

}
