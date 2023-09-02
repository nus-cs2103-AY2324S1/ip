package duke;

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

    /**
     * Constructs a new Duke instance with a given name and storage service.
     *
     * @param botName Name of the Duke chat bot.
     * @param storageService Service responsible for reading and writing tasks to storage.
     */
    public Duke(String botName, StorageService storageService) {
        this.botName = botName;
        this.taskList = new TaskList(storageService);
    }

    public static void main(String[] args) {
        OutputService outputService = new OutputService();
        UiService uiService = new UiService(outputService);
        try {
            StorageService storageService = new StorageService();
            if (storageService.wasFileCorrupted()) {
                uiService.printStorageFileCorrupted();
            }
            Duke changooseBot = new Duke("Changoose", storageService);
            TaskFactory taskFactory = new TaskFactory();
            CommandFactory commandFactory = new CommandFactory(taskFactory, changooseBot, uiService);
            CliParserService cliParserService = new CliParserService(uiService, commandFactory);

            uiService.printGreet(changooseBot.getBotName());
            cliParserService.parse();
            uiService.printBye();
        } catch (DukeStorageException e) {
            uiService.printStorageInitializationFailure();
        }
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
     * @return True if the task was added successfully, false otherwise.
     * @throws DukeStorageException If an error occurs while saving to storage.
     */
    public boolean addTask(Task task) throws DukeStorageException {
        return taskList.addTask(task);
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
