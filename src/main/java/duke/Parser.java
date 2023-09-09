package duke;

import static duke.Parser.Command.DEADLINE;
import static duke.Parser.Operation.CONFIRM;
import static duke.Parser.Operation.DATE;
import static duke.Parser.Operation.DETAILS;
import static duke.Parser.Operation.KEYWORD;
import static duke.Parser.Operation.TIME;

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
    private final Duke duke;
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
     * Can only be instantiated with a {@code Duke} object.
     *
     * @param duke  {@code Duke} object that called the constructor.
     * @param tasks {@code TaskList} object instantiated by the same {@code Duke} object.
     * @param ui    {@code Ui} object instantiated by the same {@code Duke} object.
     */
    Parser(Duke duke, duke.TaskList tasks, duke.Ui ui,
           duke.Storage storage) { // Can only be instantiated with a Duke object
        this.duke = duke;
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
            try {
                commandInExecution = Command.valueOf(message.trim().toUpperCase());
                initialiseCommand(commandInExecution);
            } catch (IllegalArgumentException e) {
                return ui.getInvalidInputMessage(message);
            }
        }
        String errorMessage = checkOperation(commandInExecution, operationInExecution, message);
        if (errorMessage != null) {
            resetCommandInExecution();
            return errorMessage;
        }
        if (operations.isEmpty()) {
            if (storedDetails != null) {
                return taskExecutionHelper(commandInExecution);
            } else {
                return duke.executeCommand(commandInExecution, message);
            }
        } else {
            Operation operation = operations.poll();
            operationInExecution = operation;
            return executeOperation(commandInExecution, operation);
        }
    }

    /**
     * Enqueues the necessary operations of the command into the LinkedList.
     *
     * @param command Command to be initialised.
     */
    public void initialiseCommand(Command command) {
        switch (command) {
        case TASK, TODO -> operations.add(DETAILS);
        case DEADLINE -> {
            operations.addLast(DETAILS);
            operations.addLast(DATE);
            operations.addLast(TIME);
        }
        case EVENT -> {
            operations.addLast(DETAILS);
            operations.addLast(DATE);
            operations.addLast(TIME);
            operations.addLast(DATE);
            operations.addLast(TIME);
        }
        case MARK, UNMARK, DELETE -> operations.addLast(CONFIRM);
        case FIND, SEARCH -> operations.addLast(KEYWORD);
        default -> {
        }
        }
    }

    /**
     * Executes the necessary operation.
     *
     * @param command   Current command in execution.
     * @param operation Current operation in execution.
     * @return Output of Duke as a String.
     */
    public String executeOperation(Command command, Operation operation) {
        switch (operation) {
        case DETAILS -> {
            return ui.getDetailsPrompt(command);
        }
        case DATE -> {
            String dateType = getDateTimeType(command);
            return ui.getDateInputMessage(command, dateType);
        }
        case TIME -> {
            String timeType = getDateTimeType(command);
            return ui.getTimeInputMessage(command, timeType);
        }
        case CONFIRM -> {
            if (tasks.isEmpty()) {
                resetCommandInExecution();
                return ui.getTasksEmptyMessage(command);
            }
            return ui.getConfirmationMessage(command, tasks);
        }
        case KEYWORD -> {
            if (tasks.isEmpty()) {
                resetCommandInExecution();
                return ui.getTasksEmptyMessage(command);
            }
            return ui.getKeywordMessage();
        }
        default -> {
            return null;
        }
        }
    }


    /**
     * Checks and handles invalid user inputs.
     *
     * @param command   Current command in execution.
     * @param operation Current operation in execution.
     * @param input     The user input.
     * @return Error message as a String, to be output by Duke; {@code null} if no errors are detected.
     */
    public String checkOperation(Command command, Operation operation, String input) {
        if (input.isBlank()) {
            return ui.getEmptyInputMessage(command);
        }
        switch (operation) {
        case DETAILS -> storedDetails = input;
        case DATE -> {
            try {
                localDates.add(LocalDate.parse(input));
            } catch (DateTimeParseException e) {
                return ui.getInvalidFormatMessage(operation);
            }
        }
        case TIME -> {
            try {
                localTimes.add(LocalTime.parse(input));
            } catch (DateTimeParseException e) {
                return ui.getInvalidFormatMessage(operation);
            }
        }
        case CONFIRM -> {
            try {
                int taskNumber = Integer.parseInt(input);
                if (taskNumber > tasks.getNumOfTasks() || taskNumber < 1) {
                    return ui.getRequestFailedMessage("task number");
                }
            } catch (NumberFormatException e) {
                return ui.getRequestFailedMessage("input");
            }
        }
        default -> {
        }
        }
        return null;
    }

    /**
     * Helper function to execute {@code Command}s which are subclasses of {@code Task}.
     *
     * @param command {@code Command} corresponding to a subclass of {@code Task}.
     * @return The appropriate {@code executeCommand} function.
     */
    public String taskExecutionHelper(Command command) {
        if (localDates.isEmpty() || localTimes.isEmpty()) {
            return duke.executeCommand(command, storedDetails);
        }
        int size = Math.min(localDates.size(), localTimes.size());
        ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            localDateTimes.add(LocalDateTime.of(localDates.get(i), localTimes.get(i)));
        }
        if (size == 1) {
            return duke.executeCommand(command, storedDetails, localDateTimes.get(0));
        } else {
            return duke.executeCommand(command, storedDetails, localDateTimes.get(0),
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
