package leon;

import static leon.Parser.Command.DEADLINE;
import static leon.Parser.Operation.CONFIRM;
import static leon.Parser.Operation.DATE;
import static leon.Parser.Operation.DETAILS;
import static leon.Parser.Operation.KEYWORD;
import static leon.Parser.Operation.TIME;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The {@code Parser} class. Deals with making sense of the user input.
 */
public class Parser {

    private final Ui ui;
    private final Leon leon;
    private final TaskList tasks;
    private final Storage storage;
    private String storedDetails;
    private final ArrayList<LocalDate> localDates = new ArrayList<>();
    private final ArrayList<LocalTime> localTimes = new ArrayList<>();
    private Command commandInExecution = Command.NONE;
    private Operation operationInExecution = Operation.NONE;
    private final LinkedList<Operation> operations = new LinkedList<>();

    /**
     * Enumeration of all possible user commands.
     */
    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, FIND, SEARCH, NONE
    }


    /**
     * Enumeration of all possible operations on the commands.
     */
    public enum Operation {
        DETAILS, DATE, TIME, CONFIRM, KEYWORD, NONE
    }


    /**
     * Constructs a new {@code Parser} object.
     * Can only be instantiated with a {@code Leon} object.
     *
     * @param leon  {@code Leon} object that called the constructor.
     * @param tasks {@code TaskList} object instantiated by the same {@code Leon} object.
     * @param ui    {@code Ui} object instantiated by the same {@code Leon} object.
     */
    Parser(Leon leon, TaskList tasks, Ui ui,
           Storage storage) { // Can only be instantiated with a Leon object
        this.leon = leon;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user input from the {@code Scanner}.
     * If the input corresponds to one of the valid commands, the {@code executeCommand} method
     * will execute the command. Otherwise, an error message will be printed. (This will be
     * treated as an invalid input.)
     *
     * @param message Input from the user.
     */
    public String readInput(String message) throws IOException {
        if (message.equals("bye")) {
            storage.saveTasksToDisk("./data/tasks.txt", tasks);
            return ui.getExitMessage(0);
        }
        if (this.commandInExecution == Command.NONE) {
            String invalidInputMessage = initialiseCommand(message);
            if (invalidInputMessage != null) {
                return invalidInputMessage;
            }
        }
        String errorMessage = checkOperation(commandInExecution, operationInExecution, message);
        if (errorMessage != null) {
            resetCommandInExecution();
            return errorMessage;
        }
        if (operations.isEmpty()) {
            return taskExecutionHelper(commandInExecution, message);
        } else {
            Operation operation = operations.poll();
            operationInExecution = operation;
            return executeOperation(commandInExecution, operation);
        }
    }

    /**
     * Aims to initialise a command corresponding to the user input. If successful,
     * enqueues the necessary operations of the command into the LinkedList. If unsuccessful,
     * returns an invalid input message.
     *
     * @param message The user input.
     * @return {@code InvalidInputMessage} if the user input is invalid; null otherwise.
     */
    public String initialiseCommand(String message) {
        try {
            commandInExecution = Command.valueOf(message.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ui.getInvalidInputMessage(message);
        }
        switch (commandInExecution) {
        case TASK:
        case TODO:
            operations.add(DETAILS);
            break;
        case DEADLINE:
            operations.addLast(DETAILS);
            operations.addLast(DATE);
            operations.addLast(TIME);
            break;
        case EVENT:
            operations.addLast(DETAILS);
            for (int i = 0; i < 2; i++) {
                operations.addLast(DATE);
                operations.addLast(TIME);
            }
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            operations.addLast(CONFIRM);
            break;
        case FIND:
        case SEARCH:
            operations.addLast(KEYWORD);
            break;
        default:
            break;
        }
        return null;
    }

    /**
     * Executes the necessary operation.
     *
     * @param command   Current command in execution.
     * @param operation Current operation in execution.
     * @return Output of Leon as a String.
     */
    public String executeOperation(Command command, Operation operation) {
        switch (operation) {
        case DETAILS:
            return ui.getDetailsPrompt(command);
        case DATE:
            String dateType = getDateTimeType(command);
            return ui.getDateInputMessage(command, dateType);
        case TIME:
            String timeType = getDateTimeType(command);
            return ui.getTimeInputMessage(command, timeType);
        case CONFIRM:
            if (tasks.isEmpty()) {
                resetCommandInExecution();
                return ui.getTasksEmptyMessage(command);
            }
            return ui.getConfirmationMessage(command, tasks);
        case KEYWORD:
            if (tasks.isEmpty()) {
                resetCommandInExecution();
                return ui.getTasksEmptyMessage(command);
            }
            return ui.getKeywordMessage();
        default:
            return null;
        }
    }


    /**
     * Checks and handles invalid user inputs.
     *
     * @param command   Current command in execution.
     * @param operation Current operation in execution.
     * @param input     The user input.
     * @return Error message as a String, to be output by Leon; {@code null} if no errors are detected.
     */
    public String checkOperation(Command command, Operation operation, String input) {
        if (input.isBlank()) {
            return ui.getEmptyInputMessage(command);
        }
        switch (operation) {
        case DETAILS:
            storedDetails = input;
            break;
        case DATE:
            if (!isValidDateInput(input)) {
                return ui.getInvalidFormatMessage(operation);
            }
            break;
        case TIME:
            if (!isValidTimeInput(input)) {
                return ui.getInvalidFormatMessage(operation);
            }
            break;
        case CONFIRM:
            if (!isValidNumberInput(input)) {
                return ui.getRequestFailedMessage("input");
            } else if (!isValidTaskNumber(Integer.parseInt(input))) {
                return ui.getRequestFailedMessage("task number");
            }
            break;
        default:
            break;
        }
        return null;
    }

    /**
     * Checks if the date input is valid for the {@code DATE} operation.
     *
     * @param input Date input by user.
     * @return true if user input is valid; false otherwise.
     */
    public boolean isValidDateInput(String input) {
        try {
            localDates.add(LocalDate.parse(input));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the time input is valid for the {@code TIME} operation.
     *
     * @param input Time input by user.
     * @return true if user input is valid; false otherwise.
     */
    public boolean isValidTimeInput(String input) {
        try {
            localTimes.add(LocalTime.parse(input));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the number input is valid for the {@code CONFIRM} operation.
     *
     * @param input Number input by user.
     * @return true if user input is valid; false otherwise.
     */
    public boolean isValidNumberInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the task number input is valid for the {@code CONFIRM} operation.
     *
     * @param taskNumber Task number input by user.
     * @return true if user input is valid; false otherwise.
     */
    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.getNumOfTasks();
    }

    /**
     * Executes {@code Command}s in the {@code Leon} class.
     *
     * @param command {@code Command} to be executed.
     * @return The appropriate {@code executeCommand} function.
     */
    public String taskExecutionHelper(Command command, String message) {
        if (storedDetails == null) {
            return leon.executeCommand(commandInExecution, message);
        }
        if (localDates.isEmpty() || localTimes.isEmpty()) {
            return leon.executeCommand(command, storedDetails);
        }
        int size = Math.min(localDates.size(), localTimes.size());
        ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            localDateTimes.add(LocalDateTime.of(localDates.get(i), localTimes.get(i)));
        }
        if (size == 1) {
            return leon.executeCommand(command, storedDetails, localDateTimes.get(0));
        } else {
            return leon.executeCommand(command, storedDetails, localDateTimes.get(0),
                localDateTimes.get(1));
        }
    }

    public String getDateTimeType(Command command) {
        if (command == DEADLINE) {
            return "due";
        } else if (command == Command.EVENT) {
            if (localDates.isEmpty() || localTimes.isEmpty()) {
                return "start";
            } else {
                return "end";
            }
        }
        return null;
    }


    /**
     * Resets all pointers, local parameters and arrays upon completion of a command.
     */
    public void resetCommandInExecution() {
        this.commandInExecution = Command.NONE;
        this.operationInExecution = Operation.NONE;
        this.storedDetails = null;
        this.localDates.clear();
        this.localTimes.clear();
        this.operations.clear();
    }
}
