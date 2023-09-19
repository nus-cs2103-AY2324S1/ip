package duke.ui;

import java.util.stream.Collectors;

import duke.TaskList;
import duke.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
                + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
                + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
                + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
                + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
        String horizontalLine = "__________________________________________________________________________";
        return horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine;
    }

    /**
     * Prints the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the error message when loading tasks from file.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

    /**
     * Prints the error message.
     *
     * @param message The error message to be printed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints the message when a task is added.
     *
     * @param task The task that is added.
     */
    public String showAddedTask(Task task) {
        return "Got it. I've added this task:\n" + task;
    }

    /**
     * Prints the message when a task is deleted.
     *
     * @param task The task that is deleted.
     */
    public String showDeletedTask(Task task) {
        return "Noted. I've removed this task:\n" + task + "\n";
    }

    /**
     * Prints the message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints the message when a task is marked as not done yet.
     *
     * @param task The task that is marked as not done yet.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(TaskList tasks) {
        String message = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + message;
    }

    /**
     * Prints the list of tasks found by the keyword.
     *
     * @param tasks The list of tasks found by the keyword.
     */
    public String showFoundTasks(TaskList tasks) {
        String message = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return "Here are the matching tasks in your list:\n" + message;
    }

    /**
     * Prints the list of tasks sorted by date.
     *
     * @param tasks The list of tasks sorted by date.
     */
    public String showSortedTasks(TaskList tasks) {
        String message = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return "Here are your deadlines sorted" + ":\n" + message;
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The current task list.
     */
    public String showNumberOfTasks(TaskList taskList) {
        return "Now you have " + taskList.getListSize() + " tasks in the list.";
    }
}
