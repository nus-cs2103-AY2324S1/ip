package anya.parser;

import anya.command.Command;
import anya.exception.AnyaException;
import anya.exception.InvalidArgumentException;
import anya.storage.Storage;
import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;
import anya.ui.Ui;

/**
 * The `Parser` class is responsible for interpreting user input
 * and executing corresponding actions in the Anya application.
 * It handles parsing commands, validating arguments,
 * and performing the appropriate operations on tasks and data storage.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new `Parser` instance with the specified storage and task list.
     *
     * @param storage The storage component responsible for saving and loading task data.
     * @param tasks   The task list containing the user's tasks.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input to be parsed.
     * @return The command represented by the user input, or {@code Command.UNKNOWN} if the input is not recognized.
     */
    public Command parseCommand(String input) {
        assert input != null : "Input cannot be null";
        input = input.toLowerCase();
        if (input.equals("bye")) {
            return Command.BYE;
        }
        if (input.equals("list")) {
            return Command.LIST;
        }
        if (input.equals("mark")) {
            return Command.MARK;
        }
        if (input.equals("unmark")) {
            return Command.UNMARK;
        }
        if (input.equals("todo")) {
            return Command.TODO;
        }
        if (input.equals("deadline")) {
            return Command.DEADLINE;
        }
        if (input.equals("event")) {
            return Command.EVENT;
        }
        if (input.equals("delete")) {
            return Command.DELETE;
        }
        if (input.equals("find")) {
            return Command.FIND;
        }
        if (input.equals("help")) {
            return Command.HELP;
        }
        return Command.UNKNOWN;
    }

    /**
     * Executes the core functionality of parsing user input and performing relevant actions.
     * This method reads user input, parses it, and executes the appropriate command based on the parsed input.
     * It handles various command types and associated error checks, displaying appropriate messages and performing
     * actions like marking tasks as done, adding tasks, deleting tasks, and more.
     *
     * @return The output of the command in String.
     */
    public String parse(String input) throws AnyaException {
        assert input != null : "Input cannot be null";
        String[] arguments = input.split(" ", 2);
        Command command = parseCommand(arguments[0]);

        String details;
        if (arguments.length == 1) {
            details = "";
        } else {
            details = arguments[1];
        }

        assert tasks != null : "Tasks list cannot be null";
        switch (command) {
        case BYE:
            return executeByeCommand();
        case LIST:
            return executeListCommand();
        case MARK: {
            return executeMarkCommand(details);
        }
        case UNMARK: {
            return executeUnmarkCommand(details);
        }
        case TODO: {
            return executeTodoCommand(details);
        }
        case DEADLINE: {
            return executeDeadlineCommand(details);
        }
        case EVENT: {
            return executeEventCommand(details);
        }
        case DELETE: {
            return executeDeleteCommand(details);
        }
        case FIND: {
            return executeFindCommand(details);
        }
        case HELP: {
            return executeHelpCommand();
        }
        default:
            throw new AnyaException("I'm sorry, but I don't know what that means (yet) :( ");
        }
    }

    private String executeHelpCommand() {
        return ui.showHelp();
    }

    private String executeFindCommand(String details) {
        TaskList matchingTasks = tasks.find(details);
        if (matchingTasks.size() == 0) {
            return ui.showTaskNotFound();
        }
        return ui.showTaskFound(matchingTasks);
    }

    private String executeDeleteCommand(String details) throws InvalidArgumentException {
        validateDeleteArguments(details);
        validateTypeOfArguments(details);
        int taskNumber = Integer.parseInt(details) - 1;
        validateTaskNumber(taskNumber);

        Task t = tasks.get(taskNumber);
        tasks.remove(t);
        return ui.showTaskDeleteSuccess(t, tasks);
    }

    private static void validateDeleteArguments(String details) throws InvalidArgumentException {
        // Error: No argument or Multiple arguments provided
        if (details.isEmpty() || details.split(" ").length != 1) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    delete <taskNumber>");
        }
    }

    private String executeEventCommand(String details) throws InvalidArgumentException {
        validateEventDetails(details);
        validateEventTime(details);

        String taskName = details.split("/from")[0].trim();
        String startTime = details.split("/from")[1].trim().split("/to")[0].trim();
        String endTime = details.split("/to")[1].trim();
        Task t = new Event(taskName, startTime, endTime);
        tasks.add(t);
        return ui.showTaskAddSuccess(t, tasks);
    }

    private static void validateEventTime(String details) throws InvalidArgumentException {
        // Error: Does not contain /from and /to
        if (!details.contains("/from") && !details.contains("/to")) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    event <taskName> /from <startTime> /to <endTime>");
        }
    }

    private static void validateEventDetails(String details) throws InvalidArgumentException {
        // Error: No argument provided
        if (details.isEmpty()) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    event <taskName> /from <startTime> /to <endTime>");
        }
    }

    private String executeDeadlineCommand(String details) throws InvalidArgumentException {
        String[] info = details.split("/by");
        validateDeadlineDetails(details, info);

        String taskName = info[0].trim();
        String deadline = info[1].trim();
        Task t = new Deadline(taskName, deadline);
        tasks.add(t);
        return ui.showTaskAddSuccess(t, tasks);
    }

    private static void validateDeadlineDetails(String details, String[] info) throws InvalidArgumentException {
        // Error: No argument or wrong no of arguments provided
        if (details.isEmpty() || info.length != 2) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    deadline <taskName> /by <deadline>");
        }
    }

    private String executeTodoCommand(String details) throws InvalidArgumentException {
        validateTodoDescription(details);

        Task t = new Todo(details);
        tasks.add(t);
        return ui.showTaskAddSuccess(t, tasks);
    }

    private static void validateTodoDescription(String details) throws InvalidArgumentException {
        // Error: No argument provided
        if (details.isEmpty()) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    todo <taskName>");
        }
    }


    private String executeByeCommand() throws AnyaException {
        storage.save(tasks);
        return ui.showExitMessage();
    }

    private String executeListCommand() throws AnyaException {
        return ui.showTaskList(tasks);
    }
    private String executeMarkCommand(String details) throws InvalidArgumentException {
        validateNumberOfArguments(details);
        validateTypeOfArguments(details);
        int taskNumber = Integer.parseInt(details) - 1;
        validateTaskNumber(taskNumber);

        Task t = tasks.get(taskNumber);
        tasks.mark(taskNumber);
        return ui.showTaskMarkSuccess(t);
    }
    private String executeUnmarkCommand(String details) throws InvalidArgumentException {
        validateNumberOfArguments(details);
        validateTypeOfArguments(details);
        int taskNumber = Integer.parseInt(details) - 1;
        validateTaskNumber(taskNumber);

        Task t = tasks.get(taskNumber);
        tasks.unmark(taskNumber);
        return ui.showTaskUnmarkSuccess(t);
    }

    private static void validateTypeOfArguments(String details) throws InvalidArgumentException {
        // Error: Argument provided is not a number
        try {
            Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    mark/unmark <taskName>");
        }
    }

    private static void validateNumberOfArguments(String details) throws InvalidArgumentException {
        // Error: No argument or Multiple arguments provided
        if (details.isEmpty() || details.split(" ").length != 1) {
            throw new InvalidArgumentException("Please input in the following format:\n"
                    + "    mark/unmark <taskName>");
        }
    }

    private void validateTaskNumber(int taskNumber) throws InvalidArgumentException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new InvalidArgumentException("I don't see a task with the number:" + (taskNumber + 1));
        }
    }
}
