package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user input and executing corresponding actions.
 * It interacts with the TaskList and Ui classes to manage tasks and user interface interactions.
 */
public class Parser {
    private final TaskList list;
    private final Ui ui;
    private boolean shouldContinue;

    /**
     * Constructs a Parser object with the specified TaskList and Ui instances.
     *
     * @param list The TaskList instance for managing tasks.
     * @param ui The Ui instance for handling user interface interactions.
     */
    public Parser(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
        this.shouldContinue = true;
    }

    /**
     * Parses user input and executes corresponding actions.
     *
     * @param input The user input to be parsed and acted upon.
     * @throws DukeException If there is an issue with parsing or executing the input.
     */
    public void parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String action = parts[0];
        String details = parts.length == 1 ? "" : parts[1];
        switch (action) {
        case "bye":
            this.shouldContinue = false;
            this.ui.exit();
            break;
        case "list":
            this.ui.listTasks(this.list);
            break;
        case "mark":
            try {
                this.list.markAsDone(Integer.parseInt(details));
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number...");
            }
            break;
        case "todo":
            if (details.equals("")) {
                throw new DukeException("So what exactly do you want to do?");
            }
            this.list.addTask(new ToDo(details));
            break;
        case "deadline":
            String[] subParts = details.split(" /by ", 2);
            try {
                LocalDateTime dateTime = formatDateTime(subParts[1]);
                this.list.addTask(new Deadline(subParts[0], dateTime));
            } catch (DateTimeParseException e) {
                throw new DukeException("Check the date time format again!");
            }
            break;
        case "event":
            String[] taskPart = details.split(" /from ", 2);
            String[] timePart = taskPart[1].split(" /to ", 2);
            try {
                LocalDateTime from = formatDateTime(timePart[0]);
                LocalDateTime to = formatDateTime(timePart[1]);
                this.list.addTask(new Event(taskPart[0], from, to));
            } catch (DateTimeParseException e) {
                throw new DukeException("Oh no! Check the date time format again!");
            }
            break;
        case "delete":
            try {
                this.list.delete(Integer.parseInt(details));
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number...");
            }
            break;
        default:
            throw new DukeException("I have no idea what that means...");
        }
    }

    /**
     * Parses a string into a LocalDateTime object using a specific format.
     *
     * @param input The input string representing a date and time.
     * @return A LocalDateTime object parsed from the input.
     * @throws DateTimeParseException If the input cannot be parsed into a valid LocalDateTime.
     */
    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns the status of the Parser indicating whether the main application loop should continue in Duke.
     *
     * @return True if the main loop should continue, false otherwise.
     */
    public boolean getStatus() {
        return this.shouldContinue;
    }
 }
