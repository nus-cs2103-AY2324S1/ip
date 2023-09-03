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
     */
    public void printGreet(String botName) {
        outputService.echo(String.format("Hello! I'm %s%nWhat can I do for you?", botName));
    }

    /**
     * Displays a farewell message.
     */
    public void printBye() {
        outputService.echo("Bye! Hope to see you again soon!");
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to be displayed.
     */
    public void printGenericMessage(String message) {
        outputService.echo(message);
    }

    /**
     * Warns the user that the task storage file has been corrupted.
     */
    public void printStorageFileCorrupted() {
        outputService.echo("Warning: The existing tasks file was corrupted and has been reset.");
    }

    /**
     * Warns the user about a failure in initializing the storage.
     */
    public void printStorageInitializationFailure() {
        outputService.echo("Warning: Error initializing storage. "
                + "Any changes made during this session will not be saved!");
    }

    /**
     * Warns the user about a failure in adding a task to the storage.
     */
    public void printStorageAddFailure() {
        outputService.echo("Failed to add task to storage! :<");
    }

    /**
     * Warns the user about a failure in deleting a task from the storage.
     */
    public void printStorageDeleteFailure() {
        outputService.echo("Failed to delete task from storage! :<");
    }

    /**
     * Warns the user about a failure in marking a task and saving changes to the storage.
     */
    public void printStorageMarkFailure() {
        outputService.echo("Failed to save marked task to storage! :<");
    }

    /**
     * Warns the user about a failure in un-marking a task and saving changes to the storage.
     */
    public void printStorageUnmarkFailure() {
        outputService.echo("Failed to save unmarked task to storage! :<");
    }

    /**
     * Prints the tasks in the taskList, prefixed by their index.
     *
     * @param taskList the taskList containing the tasks to be printed.
     */
    public void printTaskList(List<Task> taskList) {
        outputService.echo(outputService.formatTaskList(taskList));
    }

    /**
     * Notifies the user that the input command is not recognised.
     *
     * @param input the input command.
     */
    public void printUnknownCommand(String input) {
        outputService.echo(String.format("Command: %s not recognised!", input));
    }

    /**
     * Notifies the user when a task is successfully added to the taskList.
     *
     * @param task the task that was added.
     * @param taskListSize the current size of the taskList
     */
    public void printAddTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Got it. I've added this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully deleted from the taskList.
     *
     * @param task the task that was deleted.
     * @param taskListSize the current size of the taskList
     */
    public void printDeleteTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Noted. I have removed this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully marked in the taskList.
     *
     * @param task the task that was marked.
     */
    public void printMarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Nice! I've marked this task as done:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    /**
     * Notifies the user when a task is successfully unmarked from the taskList.
     *
     * @param task the task that was unmarked.
     */
    public void printUnmarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("OK, I've unmarked this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    /**
     * Notifies the user about an invalid task index provided.
     *
     * @param taskId       The invalid task ID.
     * @param taskListSize The current size of the task list.
     */
    public void printInvalidTaskIndexProvided(int taskId, int taskListSize) {
        if (taskListSize == 0) {
            outputService.echo("There are no tasks left!");
            return;
        }
        outputService.echo(String.format("Invalid Task index: %s provided.%n"
                + "Specify a number between %s - %s", taskId, 1, taskListSize + 1));
    }

    /**
     * Notifies the user about the tasks that contains the given keyword.
     *
     * @param matchedTasks the list of tasks that matches the given keyword.
     * @param keyword the keyword to match the tasks against.
     */
    public void printFoundTasks(List<Task> matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            outputService.echo(String.format("No tasks contains the keyword: %s! :<", keyword));
            return;
        }
        List<String> displayText = new ArrayList<>();
        String taskWord = matchedTasks.size() == 1
            ? "task"
            : "tasks";
        displayText.add(String.format("Found %s matching %s in your list!", matchedTasks.size(), taskWord));
        displayText.addAll(outputService.formatTaskList(matchedTasks));
        outputService.echo(displayText);
    }
}
