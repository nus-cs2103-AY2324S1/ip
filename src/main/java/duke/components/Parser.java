package duke.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;

/**
 * Represents the logic used to handle user inputs, and parse user
 * input as commands to the chatBot.
 */
public class Parser {
    private Ui ui;
    private FileStorage fileStorage;
    private TaskList fullList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs a Parser instance with the given file path.
     *
     * @param filepath The path to the file used for storing tasks.
     */
    public Parser(String filepath) {
        this.ui = new Ui();
        this.fileStorage = new FileStorage(filepath);
        this.fullList = new TaskList();
    }

    /**
     * Constructs a Parser instance with the specified parameters.
     *
     * @param fileStorage The FileStorage object to handle file operations.
     * @param fullList The TaskList object to store ttasks.
     * @param ui The Ui object to display outputs to the user.
     */
    public Parser(FileStorage fileStorage, TaskList fullList, Ui ui) {
        this.ui = ui;
        this.fileStorage = fileStorage;
        this.fullList = fullList;
    }

    /**
     * Represents the various command types that can be used in the chatBot application.
     * These command types define the actions the chatBot can perform.
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK,
        DELETE, LIST, BYE, UNKNOWN, EMPTY, FIND
    }

    /**
     * Returns a CommandType based on the user input.
     *
     * @param input User input to be parsed.
     * @return The CommandType enum representing the parsed user input.
     */
    public CommandType parseCommand(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("mark")) {
            return CommandType.MARK;
        } else if (input.equals("unmark")) {
            return CommandType.UNMARK;
        } else if (input.equals("todo")) {
            return CommandType.TODO;
        } else if (input.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.equals("event")) {
            return CommandType.EVENT;
        } else if (input.equals("delete")) {
            return CommandType.DELETE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.equals("")) {
            return CommandType.EMPTY;
        } else if (input.equals("find")) {
            return CommandType.FIND;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Converts a date string to a LocalDateTime object.
     *
     * @param date The date string in the specified format.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public LocalDateTime setDate(String date) {
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date input. Reverting to current date and time");
            return LocalDateTime.now();
        }
    }

    /**
     * Checks if the input is BYE.
     *
     * @param input The user command.
     * @return True if the user command is bye. False if user input is not bye.
     */
    public boolean isBye(String input) {
        String[] userInputParts = input.split(" ", 2);
        String commandUser = userInputParts[0];
        CommandType command = parseCommand(commandUser);

        switch (command) {
        case BYE:
            return true;
        default:
            return false;
        }
    }

    /**
     * Processes user input to create and execute task-related actions.
     *
     * @param input The user input to be processed.
     * @return {@code true} if the application should continue running. {@code false} if it should exit.
     */
    public String createTaskAction(String input) {
        String[] userInputParts = input.split(" ", 2);
        String commandUser = userInputParts[0];
        CommandType command = parseCommand(commandUser);

        switch (command) {
        case EMPTY:
            return ui.showEmptyMessage();

        case UNKNOWN:
            return ui.showUnknownMessage();

        case BYE:
            fileStorage.saveTasks(fullList);
            return ui.showByeMessage();

        case LIST:
            if (userInputParts.length > 1) {
                return ui.showInvalidMessage();
            }
            return ui.showList(fullList);

        case MARK:
            String[] splitIndexMark = input.split(" ");
            if (splitIndexMark.length <= 1 || splitIndexMark.length > 2) {
                return ui.showInvalidMessage();
            }
            int index = Integer.parseInt(splitIndexMark[1]) - 1;
            return fullList.markItem(index);

        case UNMARK:
            String[] splitIndexUnMark = input.split(" ");
            if (splitIndexUnMark.length <= 1 || splitIndexUnMark.length > 2) {
                return ui.showInvalidMessage();
            }
            int indexUnmark = Integer.parseInt(splitIndexUnMark[1]) - 1;
            return fullList.unMarkItem(indexUnmark);

        case TODO:
            if (userInputParts.length <= 1) {
                return ui.showInvalidMessage();
            }
            ToDos toDo = new ToDos(userInputParts[1].trim());
            return fullList.addToList(toDo);

        case DEADLINE:
            String[] details = userInputParts[1].split("/by");
            String description = details[0].trim();

            if (details.length <= 1) {
                return ui.showInvalidMessage();
            }

            LocalDateTime by = setDate(details[1].trim());
            Deadline deadline = new Deadline(description, by);
            return fullList.addToList(deadline);

        case EVENT:
            String[] det = userInputParts[1].split("/from");
            if (det.length <= 1) {
                return ui.showInvalidMessage();
            }
            String[] dateParts = det[1].trim().split("/to");
            if (dateParts.length <= 1) {
                return ui.showInvalidMessage();
            }

            String descr = det[0].trim();
            LocalDateTime from = setDate(dateParts[0].trim());
            LocalDateTime to = setDate(dateParts[1].trim());

            Event event = new Event(descr, from, to);

            if (event.isStartDateBefore(from, to)) {
                return fullList.addToList(event);
            } else {
                return ui.showInvalidDate();
            }

        case DELETE:
            String[] split = input.split(" ");
            if (split.length <= 1 || split.length > 2) {
                return ui.showInvalidMessage();
            }
            int ind = Integer.parseInt(split[1]) - 1;
            return fullList.deleteFromList(ind);

        case FIND:
            String[] inputParts = input.split(" ", 2);
            String keyword = inputParts[1].trim();
            TaskList list = fullList.findTask(keyword);
            if (list.getSize() == 0) {
                return ui.showNoFind();
            } else {
                return ui.showFind(list);
            }

        default:
            return ui.showInvalidMessage();
        }
    }
}
