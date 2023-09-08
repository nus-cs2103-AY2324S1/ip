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
    private final ArrayList<LocalDate> localDates = new ArrayList<>();
    private final ArrayList<LocalTime> localTimes = new ArrayList<>();
    private Command commandInExecution = Command.NONE;
    private Operation operationInExecution = Operation.NONE;
    private final LinkedList<Operation> operations = new LinkedList<>();

    /**
     * Enumeration of all possible user commands.
     */
    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, SEARCH, NONE
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
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
            if (localDates.isEmpty() || localTimes.isEmpty()) {
                return duke.executeCommand(commandInExecution, message);
            }
            int size = Math.min(localDates.size(), localTimes.size());
            ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                localDateTimes.add(LocalDateTime.of(localDates.get(i), localTimes.get(i)));
            }
            if (size == 1) {
                return duke.executeCommand(commandInExecution, message, localDateTimes.get(0));
            } else {
                return duke.executeCommand(commandInExecution, message, localDateTimes.get(0),
                    localDateTimes.get(1));
            }
        } else {
            Operation operation = operations.poll();
            operationInExecution = operation;
            return executeOperation(commandInExecution, operation);
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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
        case SEARCH -> operations.addLast(KEYWORD);
        default -> {
        }
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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
            return ui.getConfirmationMessage(command);
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public String checkOperation(Command command, Operation operation, String input) {
        String msg = null;
        switch (operation) {
        case DATE -> {
            try {
                localDates.add(LocalDate.parse(input));
            } catch (DateTimeParseException e) {
                msg = ui.getInvalidFormatMessage(operation);
            }
        }
        case TIME -> {
            try {
                localTimes.add(LocalTime.parse(input));
            } catch (DateTimeParseException e) {
                msg = ui.getInvalidFormatMessage(operation);
            }
        }
        case CONFIRM -> {
            try {
                int taskNumber = Integer.parseInt(input);
                if (taskNumber > tasks.getNumOfTasks() || taskNumber < 1) {
                    msg = ui.getRequestFailedMessage("task number");
                }
            } catch (NumberFormatException e) {
                msg = ui.getRequestFailedMessage("input");
            }
        }
        default -> {
        }
        }
        if (msg == null && input.isBlank()) {
            msg = ui.getEmptyInputMessage(command, operation);
        }
        return msg;
    }

    public String getDateTimeType(Command command) {
        if (command == DEADLINE) {
            return "due";
        } else if (command == Command.EVENT) {
            int prog = operations.size();
            if (prog >= 3) {
                return "start";
            } else {
                return "end";
            }
        }
        return null;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void resetCommandInExecution() {
        this.commandInExecution = Command.NONE;
        this.operationInExecution = Operation.NONE;
        this.localDates.clear();
        this.localTimes.clear();
        this.operations.clear();
    }
}
