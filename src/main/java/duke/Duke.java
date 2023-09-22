package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Duke is a personal assistant chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(storage.readTasks());
            this.parser = new Parser();

        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * Returns the response of Duke to the user input.
     *
     * @param input The user input.
     * @return The response of Duke to the user input.
     */
    public String getResponse(String input) {
        try {
            CommandType commandType = parser.parseCommandType(input);
            return handleCommand(commandType, input);
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        assert false : "Response should not be null";
        return "Duke heard: " + input;
    }

    private void run() {
        ui.printWelcomeMessage();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                CommandType commandType = parser.parseCommandType(input);
                handleCommand(commandType, input);
            }
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        } catch (Exception e) {
            ui.printErrorMessage(new DukeException("An unexpected error occurred: " + e.getMessage()));
        }
        ui.printFarewellMessage();
    }

    /**
     * Serves as the main entry point of the Duke application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke ekud = new Duke();
        ekud.run();
    }

    private String handleCommand(CommandType commandType, String command) throws DukeException {
        Task task;
        TaskList filteredList;

        switch (commandType) {
        case LIST:
            ui.printList(tasks.getTasks());
            return ui.displayList(tasks.getTasks());

        case MARK:
            task = markTask(command);
            return ui.displayMarkedTaskConfirmation(task);

        case DELETE:
            task = deleteTask(command);
            return ui.displayDeletedTaskConfirmation(task, tasks);

        case TODO:
        case DEADLINE:
        case EVENT:
            task = addTask(command);
            return ui.displayAddedTaskConfirmation(task, tasks);

        case FIND:
            filteredList = handleFind(command);
            return ui.displayList(filteredList.getTasks());

        case SCHEDULE:
            filteredList = findTaskByPeriod(command);
            return ui.displayList(filteredList.getTasks());

        case UNKNOWN:
            ui.printErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));
            return ui.displayErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));

        case EXIT:
            ui.printFarewellMessage();
            return ui.displayFarewellMessage();

        default:
            assert false : "Command type should not be null";
            return "";
        }
    }

    private TaskList handleFind(String command) {
        ui.printFindMessage();
        String keyword = command.split(" ")[1];
        TaskList filtered = tasks.filterByName(keyword);
        ui.printList(filtered.getTasks());
        return filtered;
    }

    private TaskList findTaskByPeriod(String command) {

        ui.printFindMessage();
        String[] split = command.split(" /from ");
        String from = split[1].split(" /to ")[0];
        String to = split[1].split(" /to ")[1];
        LocalDateTime start = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        LocalDateTime end = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));

        TaskList filtered = tasks.filterByDateRange(start, end);
        ui.printList(filtered.getTasks());
        return filtered;
    }

    private Task addTask(String task) {
        try {
            Task newTask = null;
            if (task.startsWith("todo")) {
                newTask = ToDo.createToDoFromCommand(task);
            } else if (task.startsWith("deadline")) {
                newTask = Deadline.createDeadlineFromCommand(task);
            } else if (task.startsWith("event")) {
                newTask = Event.createEventFromCommand(task);
            }

            if (newTask != null) {
                tasks.add(newTask);
                storage.write(newTask);
                ui.printAddedTaskConfirmation(newTask, tasks);
            }
            return newTask;
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        assert false : "Task should not be null";
        return null;
    }


    private Task markTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            storage.write(tasks.getTasks());
            ui.printMarkedTaskConfirmation(task);
            return task;
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }

        return null;
    }

    private Task deleteTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.remove(index);
            storage.write(tasks.getTasks());
            ui.printDeletedTaskConfirmation(task, tasks);
            return task;
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }

        return null;
    }
}
