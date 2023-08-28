import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private final String botName;
    private final List<Task> taskList;

    public Duke(String botName) {
        this.botName = botName;
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke changooseBot = new Duke("Changoose");
        OutputService outputService = new OutputService();
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

    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    public Optional<Task> deleteTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task removedTask = taskList.remove(index);
        return Optional.of(removedTask);
    }

    public Optional<Task> markTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsDone();
        return Optional.of(task);
    }

    public Optional<Task> unmarkTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsNotDone();
        return Optional.of(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }
}
