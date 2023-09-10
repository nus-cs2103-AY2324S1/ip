import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private static final String CHATBOT_NAME = "Koko";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui(CHATBOT_NAME);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
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
                    ui.printTaskList(tasks);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(remaining) - 1;
                    if (markIndex < 0 || markIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task markTarget = tasks.get(markIndex);
                    markTarget.markAsDone();
                    ui.printTaskMarkedMessage(markTarget);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(remaining) - 1;
                    if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task unmarkTarget = tasks.get(unmarkIndex);
                    unmarkTarget.markAsUndone();
                    ui.printTaskUnmarkedMessage(unmarkTarget);
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(remaining) - 1;
                    if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task toDelete = tasks.remove(deleteIndex);
                    ui.printTaskDeletedMessage(toDelete, tasks.size());
                    break;
                case "todo":
                    Todo newTodo = Todo.createFromCommandString(remaining);
                    tasks.add(newTodo);
                    ui.printTaskAddedMessage(newTodo, tasks.size());
                    break;
                case "deadline":
                    Deadline newDeadline = Deadline.createFromCommandString(remaining);
                    tasks.add(newDeadline);
                    ui.printTaskAddedMessage(newDeadline, tasks.size());
                    break;
                case "event":
                    Event newEvent = Event.createFromCommandString(remaining);
                    tasks.add(newEvent);
                    ui.printTaskAddedMessage(newEvent, tasks.size());
                    break;
                default:
                    throw new DukeException("Each message should start with one of the following commands: list, mark, unmark, todo, deadline, event");
            }

            // All valid commands except for `list` will result in the task list changing, so we should trigger
            // a disk write to save the updated task list.
            if (!command.equals("list")) {
                try {
                    FileUtils.saveTasksToFile(tasks);
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
            tasks = FileUtils.loadTasksFromFile();
        } catch (FileNotFoundException fileNotFoundException) {
            ui.printErrorMessage("Previous data file not found, starting from fresh task list.");
            tasks = new ArrayList<>();
        } catch (DukeException dukeException) {
            ui.printErrorMessage(dukeException.getMessage());
            tasks = new ArrayList<>();
        }

        ui.startUserInputLoop(this::parseInput);
        ui.exit();
    }

}
