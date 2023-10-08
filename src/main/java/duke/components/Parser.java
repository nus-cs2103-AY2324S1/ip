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
     * @param fullList The TaskList object to store tasks.
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
        DELETE, LIST, BYE, UNKNOWN, EMPTY, FIND, MASS
    }

    /**
     * Returns a CommandType based on the user input.
     *
     * @param input User input to be parsed.
     * @return The CommandType enum representing the parsed user input.
     */
    public CommandType parseCommand(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.EMPTY;
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
     * Processes user input to create and execute task-related actions.
     *
     * @param input The user input to be processed.
     * @return A String representing the action completed to be displayed to the user.
     */

    public String createTaskAction(String input) {
        String[] userInputParts = input.split(" ", 2);
        String commandUser = userInputParts[0];
        CommandType command = parseCommand(commandUser);

        switch (command) {
        case LIST:
            assert command != CommandType.LIST : "Command is not LIST";
            if (userInputParts.length > 1) {
                return ui.showInvalidMessage();
            }
            return ui.showList(fullList);

        case MARK:
            assert command != CommandType.MARK : "Command is not MARK";
            String[] splitIndexMark = input.split(" ");
            if (splitIndexMark.length <= 1 || splitIndexMark.length > 2) {
                return ui.showInvalidMessage();
            }
            int index = Integer.parseInt(splitIndexMark[1]);
            return fullList.markItem(index);

        case UNMARK:
            assert command != CommandType.UNMARK : "Command is not UNMARK";
            String[] splitIndexUnMark = input.split(" ");
            if (splitIndexUnMark.length <= 1 || splitIndexUnMark.length > 2) {
                return ui.showInvalidMessage();
            }
            int indexUnmark = Integer.parseInt(splitIndexUnMark[1]);
            return fullList.unMarkItem(indexUnmark);

        case TODO:
            assert command != CommandType.TODO : "Command is not TODO";
            if (userInputParts.length <= 1) {
                return ui.showInvalidMessage();
            }
            ToDos toDo = new ToDos(userInputParts[1].trim());
            return fullList.addToList(toDo);

        case DEADLINE:
            assert command != CommandType.DEADLINE : "Command is not DEADLINE";
            String[] details = userInputParts[1].split("/by");
            String description = details[0].trim();

            if (details.length <= 1) {
                return ui.showInvalidMessage();
            }

            LocalDateTime by = setDate(details[1].trim());
            Deadline deadline = new Deadline(description, by);
            return fullList.addToList(deadline);

        case EVENT:
            assert command != CommandType.EVENT : "Command is not EVENT";
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

            if (event.isStartBeforeEnd(from, to)) {
                return fullList.addToList(event);
            } else {
                return ui.showInvalidDate();
            }

        case DELETE:
            assert command != CommandType.DELETE : "Command is not DELETE";
            String[] split = input.split(" ");
            if (split.length <= 1 || split.length > 2) {
                return ui.showInvalidMessage();
            }
            int ind = Integer.parseInt(split[1]);
            return fullList.deleteFromList(ind);

        case FIND:
            assert command != CommandType.FIND : "Command is not FIND";
            String[] inputParts = input.split(" ", 2);
            String keyword = inputParts[1].trim();
            TaskList list = fullList.findTask(keyword);
            if (list.getSize() == 0) {
                return ui.showNoFind();
            } else {
                return ui.showFind(list);
            }

        case MASS:
            assert command != CommandType.MASS : "Command is not MASS";
            String[] inputPartsMass = input.split(" ");
            if (inputPartsMass.length <= 2) {
                return ui.showInvalidMessage();
            }

            String commandToBeDone = inputPartsMass[1].trim();

            boolean isDelete = commandToBeDone.equals("delete");
            boolean isMark = commandToBeDone.equals("mark");
            boolean isUnMark = commandToBeDone.equals("unmark");
            boolean isInvalidCommand = !isDelete && !isMark && !isUnMark;
            if (isInvalidCommand) {
                return ui.showCustomError("The command used after mass is not valid: " + commandToBeDone);
            }

            int[] indexArray = new int[inputPartsMass.length - 2];
            for (int i = 2; i < inputPartsMass.length; i++) {
                int value = Integer.parseInt(inputPartsMass[i].trim());
                indexArray[i - 2] = value;
            }

            if (isMark) {
                return fullList.markItem(indexArray);
            } else if (isUnMark) {
                return fullList.unMarkItem(indexArray);
            } else {
                return fullList.deleteFromList(indexArray);
            }

        case BYE:
            assert command != CommandType.BYE : "Command is not BYE";
            fileStorage.saveTasks(fullList);
            return ui.showByeMessage();

        case EMPTY:
            assert command != CommandType.EMPTY : "Command is not EMPTY";
            return ui.showEmptyMessage();

        case UNKNOWN:
            assert command != CommandType.UNKNOWN : "Command is not UNKNOWN";
            return ui.showUnknownMessage();

        default:
            return ui.showInvalidMessage();
        }
    }
}
