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
     * Shows user the welcome message when chatbot is started.
     */
    public String showWelcome() {
        String message = "Hello! I'm Bard.\nWhat can I do for you?";
        return message;
    }

    /**
     * Shows user the exit message when chatbot is exited.
     */
    public String showExit() {
        this.input.close();
        String message = "Bye. Hope to see you again soon!";
        return message;
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
        appendMessage(message.toString());
        return message.toString();
    }

    /**
     * Shows user a message when a task is added.
     *
     * @param task The added task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public String showAdd(Task task, int totalNumTask) {
        String message = "Got it. I've added this task:\n"
                + task + "\nNow you have " + totalNumTask + " tasks in the list.";
        return message;
    }

    /**
     * Shows user a message when a task is deleted.
     *
     * @param task The deleted task.
     * @param totalNumTask The total number of tasks in the list.
     */
    public String showDelete(Task task, int totalNumTask) {
        String message = "Noted. I've removed this task:\n"
                + task + "\nNow you have " + totalNumTask + " tasks in the list.";
        return message;
    }

    /**
     * Shows user a message when a task is marked as done.
     *
     * @param task The task marked as done.
     */
    public String showDone(Task task) {
        String message = "Nice! I've marked this task as done:\n";
        return message + task;
    }

    /**
     * Shows user a message when a task is marked as not done.
     *
     * @param task The task marked as not done.
     */
    public String showNotDone(Task task) {
        String message = "OK, I've marked this task as NOT done yet:\n";
        return message + task;
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
        boolean hasTask = !matchingTasks.isEmpty();
        int taskNumber = 1;

        if (hasTask) {
            for (Task task : matchingTasks) {
                message.append("\n").append(taskNumber).append(". ").append(task);
                taskNumber++;
            }
        } else {
            message.append("\nYay! You have no tasks on ")
                    .append(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))).append(" :D");
        }

        appendMessage(message.toString());
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
        boolean hasTask = !matchingTasks.isEmpty();
        int taskNumber = 1;

        if (hasTask) {
            for (Task task : matchingTasks) {
                message.append("\n").append(taskNumber).append(". ").append(task);
                taskNumber++;
            }
        } else {
            message.append("\nYay! You have no tasks today :D");
        }

        appendMessage(message.toString());
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
        appendMessage(message.toString());
        return message.toString();
    }

    /**
     * Shows user the list of commands.
     */
    public String showListOfCommands() {
        String message = "Here are the available commands:\n"
                + "1. todo <description>: Add a new todo task.\n"
                + "2. deadline <description> /by <datetime>: Add a new deadline task.\n"
                + "3. event <description> /at <datetime>: Add a new event task.\n"
                + "4. list: List all tasks.\n"
                + "5. mark <task_index>: Mark a task as done.\n"
                + "6. unmark <task_index>: Mark a task as not done.\n"
                + "7. delete <task_index>: Delete a task.\n"
                + "8. find <keyword>: Find tasks by keyword.\n"
                + "9. help: Show this help message.\n"
                + "10. bye: Exit the application.";
        return message;
    }

    /**
     * Shows user an error message for Duke exceptions.
     *
     * @param e The Duke exception to display.
     */
    public String showDukeException(DukeException e) {
        String message = e.getMessage();
        return message;
    }

    /**
     * Shows user an error message for invalid date-time format.
     */
    public String showInvalidDateTimeFormat() {
        String message = "Please enter the time in the format of <d/M/yyyy HHmm>!";
        return message;
    }

    /**
     * Shows user an error message for invalid date format.
     */
    public String showInvalidDateFormat() {
        String message = "Please enter the date in the format of <d/M/yyyy>!";
        return message;
    }

    /**
     * Shows user an error message for saving tasks to data file.
     */
    public String showSavingError() {
        String message = "Error saving tasks to the data file.";
        return message;
    }

    /**
     * Shows user an error message for array index out of bounds.
     */
    public String showArrayIndexOutOfBoundsException() {
        String message = "☹ OOPS!!! Please provide a valid task number.";
        return message;
    }

    /**
     * Appends the message to the StringBuilder.
     *
     * @param message Message to append.
     */
    private void appendMessage(String message) {
        messages.append(message).append("\n");
    }

}
