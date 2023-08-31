import exception.DukeException;
import exception.DukeStorageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private final String botName;
    private final List<Task> taskList;
    private final StorageService storageService;

    public Duke(String botName, StorageService storageService) {
        this.botName = botName;
        this.taskList = new ArrayList<>();
        this.storageService = storageService;
    }

    public static void main(String[] args) {
        OutputService outputService = new OutputService();
        StorageService storageService = null;
        try {
            storageService = new StorageService();
        } catch (DukeStorageException e) {
            outputService.echo("Warning: Error initializing storage. " +
                    "Any changes made during this session will not be saved!");
        }
        assert storageService != null;
        if (storageService.wasFileCorrupted()) {
            outputService.echo("Warning: The existing tasks file was corrupted and has been reset.");
        }
        Duke changooseBot = new Duke("Changoose", storageService);
        TaskFactory taskFactory = new TaskFactory();
        CliParserService cliParserService = new CliParserService(changooseBot, outputService, taskFactory);
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", changooseBot.getBotName());
        String endMessage = "Bye! Hope to see you again soon!";

        outputService.echo(startMessage);
        cliParserService.parse();
        outputService.echo(endMessage);
    }

    public String getBotName() {
        return botName;
    }

    public boolean addTask(Task task) throws DukeStorageException {
        storageService.saveTask(task);
        return this.taskList.add(task);
    }

    public Optional<Task> deleteTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        storageService.deleteTask(index);
        Task removedTask = taskList.remove(index);
        return Optional.of(removedTask);
    }

    public Optional<Task> markTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    public Optional<Task> unmarkTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsNotDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }
}
