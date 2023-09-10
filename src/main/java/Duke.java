import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static final String CHATBOT_NAME = "Koko";
    private final Ui ui;
    private final Storage storage;

    private TaskList taskList;


    public Duke(String filePath) {
        ui = new Ui(CHATBOT_NAME);
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void parseInput(String input) {
        try {
            if (input.contains("|")) {
                throw new DukeException("Input cannot contain pipe (|) character!");
            }
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String remaining = parts.length > 1 ? parts[1] : "";

            if (remaining.isEmpty()) {
                switch (command) {
                    case "mark":
                    case "unmark":
                        throw new DukeException("Please specify a task number.");
                    case "todo":
                        throw new DukeException("Description for todo cannot be empty.");
                    case "deadline":
                        throw new DukeException("Description and date for deadline cannot be empty.");
                    case "event":
                        throw new DukeException("Description, start date, and end date for event cannot be empty.");
                }
            }

            switch (command) {
                case "list":
                    ui.printTaskList(taskList);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(remaining) - 1;
                    Task markedTask = taskList.markTaskAtIndex(markIndex);
                    ui.printTaskMarkedMessage(markedTask);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(remaining) - 1;
                    Task unmarkedTask = taskList.unmarkTaskAtIndex(unmarkIndex);
                    ui.printTaskUnmarkedMessage(unmarkedTask);
                    break;
                case "delete":
                    Task deletedTask = taskList.deleteTaskAtIndex(Integer.parseInt(remaining) - 1);
                    ui.printTaskDeletedMessage(deletedTask, taskList.size());
                    break;
                case "todo":
                    Todo newTodo = Todo.createFromCommandString(remaining);
                    taskList.addTask(newTodo);
                    ui.printTaskAddedMessage(newTodo, taskList.size());
                    break;
                case "deadline":
                    Deadline newDeadline = Deadline.createFromCommandString(remaining);
                    taskList.addTask(newDeadline);
                    ui.printTaskAddedMessage(newDeadline, taskList.size());
                    break;
                case "event":
                    Event newEvent = Event.createFromCommandString(remaining);
                    taskList.addTask(newEvent);
                    ui.printTaskAddedMessage(newEvent, taskList.size());
                    break;
                default:
                    throw new DukeException("Each message should start with one of the following commands: list, mark, unmark, todo, deadline, event");
            }

            // All valid commands except for `list` will result in the task list changing, so we should trigger
            // a disk write to save the updated task list.
            if (!command.equals("list")) {
                try {
                    storage.saveTasksToFile(taskList);
                } catch (IOException ioException) {
                    throw new DukeException("Error while saving task list to file!");
                }
            }

        } catch (NumberFormatException e) {
            ui.printErrorMessage("Please enter a valid task number.");
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

    public void run() {
        ui.greet();

        try {
            taskList = storage.loadTasksFromFile();
            ui.showLoadedTasks(taskList);
        } catch (FileNotFoundException fileNotFoundException) {
            ui.printErrorMessage("Previous data file not found, starting from fresh task list.");
            taskList = new TaskList();
        } catch (DukeException dukeException) {
            ui.printErrorMessage(dukeException.getMessage());
            taskList = new TaskList();
        }

        ui.startUserInputLoop(this::parseInput);
        ui.exit();
    }

}
