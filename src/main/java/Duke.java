import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showErrorMessage("Something went wrong with loading the tasks", e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        boolean isRunning = true;

        ui.showWelcomeMessage();
        if (tasks.getSize() != 0) {
            ui.showLoadedTasksMessage();
        }

        while (isRunning) {
            String[] splitInput = ui.readInput().split(" ", 2);
            String command = splitInput[0];
            switch (command) {
                case "bye":
                    ui.closeAndGoodbyeMessage();
                    try {
                        storage.saveData(tasks);
                        isRunning = false;
                    } catch (IOException e) {
                        ui.showErrorMessage("Something went wrong with saving the tasks", e);
                    }
                    break;
                case "list": {
                    ui.listTasks(tasks);
                    break;
                }
                case "mark": {
                    ui.showDoneMessage(tasks.getTask(Integer.parseInt(splitInput[1]) - 1).markAsDone());
                    break;
                }
                case "unmark": {
                    ui.showUndoneMessage(tasks.getTask(Integer.parseInt(splitInput[1]) - 1).markAsUndone());
                }
                case "todo": {
                    if (splitInput.length == 1) {
                        ui.showErrorMessage("The description of a todo cannot be empty\n");
                        break;
                    }
                    Task task = new Todo(splitInput[1]);
                    tasks.addTask(task);
                    ui.showAddMessage(tasks.getSize(), task);
                    break;
                }
                case "deadline": {
                    String[] splitInputBy = splitInput[1].split(" /by ", 2);
                    Task task = new Deadline(splitInputBy[0], LocalDateTime.parse(splitInputBy[1], dateTimeFormat));
                    tasks.addTask(task);
                    ui.showAddMessage(tasks.getSize(), task);
                    break;
                }
                case "event": {
                    String[] splitInputFrom = splitInput[1].split(" /from ", 2);
                    String[] splitInputTo = splitInputFrom[1].split(" /to ", 2);
                    Task task = new Event(splitInputFrom[0], LocalDateTime.parse(splitInputTo[0], dateTimeFormat),
                            LocalDateTime.parse(splitInputTo[1], dateTimeFormat));
                    tasks.addTask(task);
                    ui.showAddMessage(tasks.getSize(), task);
                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task task = tasks.getTask(index);
                    tasks.removeTask(index);
                    ui.showDeleteMessage(tasks.getSize(), task);
                    break;
                }
                default:
                    ui.showErrorMessage("I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toAbsolutePath().toString()).run();
    }
}
