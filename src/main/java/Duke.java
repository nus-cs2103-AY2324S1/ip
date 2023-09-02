import exception.DukeStorageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Duke {
    private final String botName;
    private final List<Task> taskList;
    private final StorageService storageService;

    public Duke(String botName, StorageService storageService) {
        this.botName = botName;
        this.storageService = storageService;
        this.taskList = new ArrayList<>(storageService.loadTasks());
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
            CliParserService cliParserService = new CliParserService(changooseBot, uiService, taskFactory);

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
