package duke.service;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * Provides a set of methods for displaying user interface messages and task information.
 * <p>
 * This service acts as an intermediate between the logic and the actual output service. It simplifies
 * the process of generating structured messages for different operations within the application.
 * </p>
 */
public class UiService {
    private final OutputService outputService;

    /**
     * Creates a new UiService instance with a specified OutputService.
     *
     * @param outputService The service to handle actual output operations.
     */
    public UiService(OutputService outputService) {
        this.outputService = outputService;
    }

    /**
     * Displays a greeting message with the given bot's name.
     *
     * @param botName Name of the bot.
     * @return A String containing the greeting message.
     */
    public String greetMessage(String botName) {
        return String.format("Hello! I'm %s%nWhat can I do for you?", botName);
    }

    /**
     * Displays a farewell message.
     *
     * @return A string containing the farewell message.
     */
    public String formatBye() {
        return outputService.echo("Bye! Hope to see you again soon!");
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to be displayed.
     * @return The message to be displayed, formatted by OutputService.
     */
    public String formatGenericMessage(String message) {
        return outputService.echo(message);
    }

    /**
     * Displays a generic message to the user.
     *
     * @param messages The List of messages to be displayed.
     * @return The messages to be displayed, formatted by OutputService.
     */
    public String formatGenericMessage(List<String> messages) {
        return outputService.echo(messages);
    }

    /**
     * Warns the user that the task storage file has been corrupted.
     *
     * @return A string containing the warning message about the corrupted task storage file.
     */
    public String storageFileCorruptedMessage() {
        return "Warning: The existing tasks file was corrupted and has been reset.";
    }

    /**
     * Warns the user about a failure in initializing the storage.
     *
     * @return A string containing the warning message about the failed storage initialization.
     */
    public String formatStorageInitializationFailure() {
        return outputService.echo("Warning: Error initializing storage. "
                + "Any changes made during this session will not be saved!");
    }

    /**
     * Warns the user about a failure in adding a task to the storage.
     *
     * @return A string containing the warning message about the failed addition of the task to storage.
     */
    public String formatStorageAddFailure() {
        return outputService.echo("Failed to add task to storage! :<");
    }

    /**
     * Warns the user about a failure in deleting a task from the storage.
     *
     * @return A string containing the warning message about the failed deletion of the task from storage.
     */
    public String formatStorageDeleteFailure() {
        return outputService.echo("Failed to delete task from storage! :<");
    }

    /**
     * Warns the user about a failure in marking a task and saving changes to the storage.
     *
     * @return A string containing the warning message about the failed saving of the marked task to storage.
     */
    public String formatStorageMarkFailure() {
        return outputService.echo("Failed to save marked task to storage! :<");
    }

    /**
     * Warns the user about a failure in un-marking a task and saving changes to the storage.
     *
     * @return A string containing the warning message about the failed saving of the unmarked task to storage.
     */
    public String formatStorageUnmarkFailure() {
        return outputService.echo("Failed to save unmarked task to storage! :<");
    }

    /**
     * Prints the tasks in the taskList, prefixed by their index.
     *
     * @param taskList the taskList containing the tasks to be formatted.
     * @return A string representation of the task list.
     */
    public String formatTaskList(List<Task> taskList) {
        return outputService.echo(outputService.formatTaskList(taskList));
    }

    /**
     * Notifies the user when a task is successfully added to the taskList.
     *
     * @param task the task that was added.
     * @param taskListSize the current size of the taskList
     * @return A string indicating the status of the task list after adding a task.
     */
    public String formatAddTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Got it. I've added this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        return outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully deleted from the taskList.
     *
     * @param task the task that was deleted.
     * @param taskListSize the current size of the taskList
     * @return A string indicating the status of the task list after deleting a task.
     */
    public String formatDeleteTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Noted. I have removed this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        return outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully marked in the taskList.
     *
     * @param task the task that was marked.
     * @return A string indicating the status of the task after marking it.
     */
    public String formatMarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Nice! I've marked this task as done:");
        displayText.add(outputService.indentLeft(task.toString()));
        return outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully unmarked from the taskList.
     *
     * @param task the task that was unmarked.
     * @return A string indicating the status of the task after unmarking it.
     */
    public String formatUnmarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("OK, I've unmarked this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        return outputService.echo(displayText);
    }

    /**
     * Notifies the user about an invalid task index provided.
     *
     * @param taskId       The invalid task ID.
     * @param taskListSize The current size of the task list.
     * @return A string message indicating the invalidity of the task index.
     */
    public String formatInvalidTaskIndexProvided(int taskId, int taskListSize) {
        if (taskListSize == 0) {
            return outputService.echo("There are no tasks left!");
        }
        return outputService.echo(String.format("Invalid Task index: %s provided.%n"
                + "Specify a number between %s - %s", taskId, 1, taskListSize + 1));
    }

    /**
     * Notifies the user about the tasks that contains the given keyword.
     *
     * @param matchedTasks the list of tasks that matches the given keyword.
     * @param keyword the keyword to match the tasks against.
     * @return A string representing the list of tasks that matches the given keyword. If no tasks are found,
     *         return a string indicating that there are no tasks that matches the keyword.
     */
    public String formatFoundTasks(List<Task> matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            return outputService.echo(String.format("No tasks contains the keyword: %s! :<", keyword));
        }
        List<String> displayText = new ArrayList<>();
        String taskWord = matchedTasks.size() == 1
            ? "task"
            : "tasks";
        displayText.add(String.format("Found %s matching %s in your list!", matchedTasks.size(), taskWord));
        displayText.addAll(outputService.formatTaskList(matchedTasks));
        return outputService.echo(displayText);
    }
}
