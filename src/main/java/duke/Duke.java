package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duke.exception.DukeStorageException;
import duke.service.CliParserService;
import duke.service.CommandFactory;
import duke.service.OutputService;
import duke.service.StorageService;
import duke.service.TaskFactory;
import duke.service.UiService;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Duke is responsible for task management, and exposes various methods to manipulate the Task List.
 */
public class Duke {
    private final String botName;
    private final TaskList taskList;
    private final UiService uiService;
    private final CliParserService cliParserService;

    /**
     * No-args constructor for Duke. Sets up the various services.
     */
    public Duke() {
        this.botName = "Changoose";
        this.taskList = new TaskList();
        OutputService outputService = new OutputService();
        this.uiService = new UiService(outputService);
        TaskFactory taskFactory = new TaskFactory();
        CommandFactory commandFactory = new CommandFactory(taskFactory, this, uiService);
        this.cliParserService = new CliParserService(uiService, commandFactory);
    }

    /**
     * Initializes the StorageService and attempts to load the stored tasks into taskList.
     *
     * @return A string containing the greet message, and any additional info about the initialization of the
     *         StorageService.
     */
    public String initStorage() {
        try {
            StorageService storageService = new StorageService();
            List<String> displayText = new ArrayList<>();
            if (storageService.wasFileCorrupted()) {
                displayText.add(uiService.storageFileCorruptedMessage());
            }
            taskList.loadFromStorage(storageService);
            displayText.add(uiService.greetMessage(getBotName()));
            return uiService.formatGenericMessage(displayText);
        } catch (DukeStorageException e) {
            return uiService.formatStorageInitializationFailure();
        }
    }

    /**
     * Returns the result of parsing and executing the input.
     *
     * @param input The given input to be parsed and executed.
     * @return A string representing the result of executing the parsed input.
     */
    public String getResponse(String input) {
        return cliParserService.parse(input);
    }

    /**
     * Returns the name of the Duke instance.
     *
     * @return the name of the Duke instance.
     */
    public String getBotName() {
        return botName;
    }

    /**
     * Adds a new task to the internal task list.
     *
     * @param task The task to be added.
     * @throws DukeStorageException If an error occurs while saving to storage.
     */
    public void addTask(Task task) throws DukeStorageException {
        taskList.addTask(task);
    }

    /**
     * Deletes a task based on its index in the task list.
     *
     * @param index The index of the task to be deleted.
     * @return An optional containing the deleted task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> deleteTask(int index) throws DukeStorageException {
        return taskList.deleteTask(index);
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param index The index of the task to be marked.
     * @return An optional containing the marked task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> markTask(int index) throws DukeStorageException {
        return taskList.markTask(index);
    }

    /**
     * Unmarks a task, indicating it's not completed.
     *
     * @param index The index of the task to be unmarked.
     * @return An optional containing the unmarked task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> unmarkTask(int index) throws DukeStorageException {
        return taskList.unmarkTask(index);
    }

    public List<Task> searchTasks(String keyword) {
        return taskList.searchTasks(keyword);
    }

    /**
     * Retrieves the list of tasks currently managed by Duke.
     *
     * @return A list of tasks.
     */
    public List<Task> getTaskList() {
        return taskList.getTaskList();
    }

    /**
     * Returns the total number of tasks currently managed by Duke.
     *
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.getNumberOfTasks();
    }
}
