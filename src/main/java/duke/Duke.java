package duke;

import duke.exception.DukeStorageException;
import duke.service.*;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;
import java.util.Optional;

public class Duke {
    private final String botName;
    private final TaskList taskList;

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

    public String getBotName() {
        return botName;
    }

    public boolean addTask(Task task) throws DukeStorageException {
        return taskList.addTask(task);
    }

    public Optional<Task> deleteTask(int index) throws DukeStorageException {
        return taskList.deleteTask(index);
    }

    public Optional<Task> markTask(int index) throws DukeStorageException {
        return taskList.markTask(index);
    }

    public Optional<Task> unmarkTask(int index) throws DukeStorageException {
        return taskList.unmarkTask(index);
    }

    public List<Task> getTaskList() {
        return taskList.getTaskList();
    }

    public int getNumberOfTasks() {
        return taskList.getNumberOfTasks();
    }
}
