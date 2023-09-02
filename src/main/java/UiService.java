import java.util.ArrayList;
import java.util.List;

public class UiService {
    private final OutputService outputService;

    public UiService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void printGreet(String botName) {
        outputService.echo(String.format("Hello! I'm %s%nWhat can I do for you?", botName));
    }

    public void printBye() {
        outputService.echo("Bye! Hope to see you again soon!");
    }

    public void printGenericMessage(String message) {
        outputService.echo(message);
    }

    public void printStorageFileCorrupted() {
        outputService.echo("Warning: The existing tasks file was corrupted and has been reset.");
    }

    public void printStorageInitializationFailure() {
        outputService.echo("Warning: Error initializing storage. " +
                "Any changes made during this session will not be saved!");
    }

    public void printStorageAddFailure() {
        outputService.echo("Failed to add task to storage! :<");
    }

    public void printStorageDeleteFailure() {
        outputService.echo("Failed to delete task from storage! :<");
    }

    public void printStorageMarkFailure() {
        outputService.echo("Failed to save marked task to storage! :<");
    }

    public void printStorageUnmarkFailure() {
        outputService.echo("Failed to save unmarked task to storage! :<");
    }

    public void printTaskList(List<Task> taskList) {
        outputService.printTasks(taskList);
    }

    public void printUnknownCommand(String input) {
        outputService.echo(String.format("Command: %s not recognised!", input));
    }

    public void printAddTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Got it. I've added this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        outputService.echo(displayText);
    }

    public void printDeleteTask(Task task, int taskListSize) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Noted. I have removed this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        displayText.add(String.format("Now you have %s %s in the list.",
                taskListSize,
                taskListSize == 1 ? "task" : "tasks"));
        outputService.echo(displayText);
    }

    public void printMarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("Nice! I've marked this task as done:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    public void printUnmarkTask(Task task) {
        List<String> displayText = new ArrayList<>();
        displayText.add("OK, I've unmarked this task:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    public void printInvalidTaskIndexProvided(int taskId, int taskListSize) {
        if (taskListSize == 0) {
            outputService.echo("There are no tasks left!");
            return;
        }
        outputService.echo(String.format("Invalid Task index: %s provided.%n" +
                "Specify a number between %s - %s", taskId, 1, taskListSize + 1));
    }
}
