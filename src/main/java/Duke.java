import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        ui = new UI();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private void run() {
        // Welcome message
        ui.showWelcome();
        boolean isExit = false;
        // Program only exits when user enters "bye" command
        do {
            try {
                String fullCommand = ui.readCommand();
                System.out.println();
                Command command = parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException exception) {
                ui.printMessage(exception.getMessage());
            } catch (NumberFormatException exception) {
                ui.printMessage("Error: Task number must be an integer.\n(example: mark 1)");
            } catch (DateTimeParseException exception) {
                ui.printMessage("Invalid Datetime Format: it should be dd-mm-yyyy hh:mm!");
            }
        } while (!isExit);
    }

    private String[] getEventParams(String[] tokens) throws DukeException {
        String commands = tokens[1];

        // Ensure that "/from" comes before "/to"
        if (
            !commands.contains("/from") || !commands.contains("/to")
            || commands.indexOf("/from") > commands.indexOf("/to")
        ) {
            throw new DukeException(DukeExceptionType.INVALID_EVENT_FORMAT);
        }

        // Split by /from or /to
        return commands.split("\\s+/\\w+");
    }

    private void addDeadline(String[] tokens) throws DukeException, IOException, DateTimeParseException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.DEADLINE_NO_DESCRIPTION);
        }

        // Ensure that "/by" exists in the command
        if (!tokens[1].contains("/by")) {
            throw new DukeException(DukeExceptionType.INVALID_DEADLINE_FORMAT);
        }

        // Split by "by"
        String[] deadlineParams = tokens[1].split("/by");

        // Deadline attributes
        String deadlineDescription = deadlineParams[0].trim();
        String by = deadlineParams[1].trim();

        // Ensure deadline date is a Datetime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(by, formatter);

        // Add deadline to tasks
        Task task = new Deadline(deadlineDescription, localDateTime);
        taskList.addTask(task);
        storage.write(taskList.getTasks());
        ui.showAddedTask(task, taskList.getTasks());
    }

    private void addEvent(String[] tokens) throws DukeException, IOException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.EVENT_NO_DESCRIPTION);
        }

        String[] eventParams = getEventParams(tokens);

        // Event attributes
        String eventDescription = eventParams[0].trim();
        String from = eventParams[1].trim();
        String to = eventParams[2].trim();

        // LocalDateTime Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Add event to tasks
        taskList.addTask(new Event(eventDescription, LocalDateTime.parse(from, formatter),
                LocalDateTime.parse(to, formatter)));
        storage.write(taskList.getTasks());
    }

    private void addToDo(String[] tokens) throws DukeException, IOException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.TODO_NO_DESCRIPTION);
        } else {
            taskList.addTask(new ToDo(tokens[1]));
            storage.write(taskList.getTasks());
        }
    }

    private void due(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.DUE_NO_DATE);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(tokens[1].trim(), formatter);
            ui.showTasksDueOn(date, taskList.getTasks());
        }
    }
}