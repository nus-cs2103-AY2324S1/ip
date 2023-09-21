package duke.main;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UndoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidSyntaxException;
import duke.task.TaskList;

/**
 * A class parsing commands from user input
 */
public class Parser {

    /**
     * An enum of valid commands
     */
    public enum ValidCommand {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, UNKNOWN, FIND, INCORRECTFORMAT, UNDO
    }

    /**
     * The current command from user input
     */
    private ValidCommand currentCommand;

    /**
     * Returns Command object based on user input.
     *
     * @param inp The String array containing the user input
     * @param taskList The list of tasks under the user
     * @return Command
     * @throws DukeException if the format of Command is wrong
     */
    public Command parse(String[] inp, TaskList taskList) throws DukeException {
        String title = "";
        String startDate = "";
        String endDate = "";
        int taskIndex = 0;
        boolean isValid = false;
        currentCommand = commandConverter(inp[0]);
        if (currentCommand != ValidCommand.UNKNOWN) {
            if (commandSorter(currentCommand).equals("update")) {
                if (inp.length != 2) {
                    currentCommand = ValidCommand.INCORRECTFORMAT;
                } else {
                    try {
                        taskIndex = Integer.parseInt(inp[1]);
                    } catch (NumberFormatException e) {
                        currentCommand = ValidCommand.INCORRECTFORMAT;
                    }
                }
            } else if (commandSorter(currentCommand).equals("short_comm")
                    || commandSorter(currentCommand).equals("event")) {
                if (commandSorter(currentCommand).equals("event")) {
                    startDate = startDateGetter(inp);
                }
                title = titleGetter(inp, currentCommand);
                endDate = endDateGetter(inp, currentCommand);
                isValid = commandValidator(inp, currentCommand);
            }
        }
        return commandHandler(inp, isValid, title, startDate, endDate,
                taskIndex, currentCommand, taskList);
    }

    /**
     * Returns Command of enum type based on user input
     *
     * @param value The String containing command from user input
     * @return Command of enum type
     */
    public static ValidCommand commandConverter(String value) {
        try {
            ValidCommand val = ValidCommand.valueOf(value.toUpperCase());
            return val;
        } catch (IllegalArgumentException e) {
            return ValidCommand.UNKNOWN;
        }
    }

    /**
     * Returns Command based on user input
     *
     * @param inp The String containing command from user input
     * @param isValid Returns whether the task input is valid or not
     * @param title The description of the task
     * @param startDate The String containing startDate of task if present
     * @param endDate The String containing endDate of task if present
     * @return Command
     * @throws DukeException if the format of Command is wrong
     */
    public static Command taskHandler(String[] inp, boolean isValid, String title, String startDate,
                                      String endDate) throws DukeException {
        if (inp.length < 2 || !isValid) {
            throw new InvalidSyntaxException("The description of a " + inp[0] + " cannot be empty.");
        }
        if (inp[0].equals("todo")) {
            AddCommand temp = new AddCommand("T", title, "", "");
            return temp;
        }
        if (inp[0].equals("deadline")) {
            if (endDate.equals("")) {
                throw new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
            }
            AddCommand temp = new AddCommand("D", title, "", endDate);
            return temp;
        }
        if (inp[0].equals("event")) {
            if (startDate.equals("")) {
                throw new InvalidSyntaxException("The start date of a " + inp[0] + " cannot be empty.");
            }
            if (endDate.equals("")) {
                throw new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
            }
            AddCommand temp = new AddCommand("E", title, startDate, endDate);
            return temp;
        }
        return null;
    }

    /**
     * Returns Command for Mark, Unmark and Delete if task index is valid
     *
     * @param taskIndex The task index based on user input
     * @param command The command based on user input
     * @param taskList The user's task list
     * @return Command for Mark, Unmark and Delete if task index is valid
     * @throws DukeException if the task index is invalid
     */
    public static Command taskIndexValidator(int taskIndex, ValidCommand command,
                                             TaskList taskList) throws DukeException {
        if (taskIndex > taskList.size() || taskIndex < 1) {
            throw new InvalidSyntaxException("The task does not exist.");
        }
        assert taskIndex >= 0 && taskIndex <= taskList.size() : "Task index should be valid";
        if (command == ValidCommand.MARK) {
            MarkCommand temp = new MarkCommand(taskIndex);
            return temp;
        } else if (command == ValidCommand.UNMARK) {
            UnmarkCommand temp = new UnmarkCommand(taskIndex);
            return temp;
        } else {
            DeleteCommand temp = new DeleteCommand(taskIndex);
            return temp;
        }

    }

    /**
     * Returns Command based on user input
     *
     * @param inp The String containing command from user input
     * @param isValid Returns whether the task input is valid or not
     * @param title The description of the task
     * @param startDate The String containing startDate of task if present
     * @param endDate The String containing endDate of task if present
     * @param taskIndex The task index based on user input
     * @param currentCommand The command based on user input
     * @param taskList The user's task list
     * @return Command based on user input
     * @throws DukeException if the task index or input format is invalid
     */
    public static Command commandHandler(String[] inp, boolean isValid, String title, String startDate, String endDate,
                                         int taskIndex, ValidCommand currentCommand,
                                         TaskList taskList) throws DukeException {
        switch (currentCommand) {
        case UNKNOWN:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        case INCORRECTFORMAT:
            throw new InvalidSyntaxException("The format of the command is invalid.");
        case UNDO:
            return new UndoCommand();
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(title);
        case UNMARK:
        case MARK:
        case DELETE:
            return taskIndexValidator(taskIndex, currentCommand, taskList);
        default:
            return taskHandler(inp, isValid, title, startDate, endDate);
        }
    }

    /**
     * Returns type of command
     *
     * @param currentCommand The current command entered by user
     * @return type of command
     */
    public static String commandSorter(ValidCommand currentCommand) {
        switch(currentCommand) {
        case MARK:
        case UNMARK:
        case DELETE:
            return "update";
        case TODO:
        case DEADLINE:
        case FIND:
            return "short_comm";
        case EVENT:
            return "event";
        default:
            return "Invalid";
        }
    }

    /**
     * Returns whether the description of the task is valid
     *
     * @param inp The String containing command from user input
     * @param command The current command entered by user
     * @return whether the description of the task is valid
     */
    public static boolean commandValidator(String[] inp, ValidCommand command) {
        assert inp.length >= 1 : "inp should not be empty";
        int i = 1;
        String keyword;
        if (command == ValidCommand.DEADLINE) {
            keyword = "/by";
        } else {
            keyword = "/from";
        }
        for (; i < inp.length; i++) {
            if (inp[i].equals(keyword)) {
                break;
            }
            if (i == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the description for the task
     *
     * @param inp The String array for the user input
     * @param command The current command entered by user
     * @return the description for the task
     */
    public static String titleGetter(String[] inp, ValidCommand command) {
        assert inp.length >= 1 : "inp should not be empty";
        int i = 1;
        String keyword;
        if (command == ValidCommand.DEADLINE) {
            keyword = "/by";
        } else {
            keyword = "/from";
        }
        String title = "";
        for (; i < inp.length; i++) {
            if (inp[i].equals(keyword)) {
                break;
            }
            if (title.equals("")) {
                title = inp[i];
            } else {
                title += " " + inp[i];
            }
        }
        return title;
    }

    /**
     * Returns the endDate for the task
     *
     * @param inp The String array for the user input
     * @param command The current command entered by user
     * @return the endDate for the task
     */
    public static String endDateGetter(String[] inp, ValidCommand command) {
        assert inp.length >= 1 : "inp should not be empty";
        int i = 1;
        String keyword;
        if (command == ValidCommand.DEADLINE) {
            keyword = "/by";
        } else {
            keyword = "/to";
        }
        String endDate = "";
        for (; i < inp.length; i++) {
            if (inp[i].equals(keyword)) {
                break;
            }
        }
        for (int j = i + 1; j < inp.length; j++) {
            if (endDate.equals("")) {
                endDate = inp[j];
            } else {
                endDate += " " + inp[j];
            }
        }
        return endDate;
    }

    /**
     * Returns the startDate for the task
     *
     * @param inp The String array for the user input
     * @return the startDate for the task
     */
    public static String startDateGetter(String[] inp) {
        assert inp.length >= 1 : "inp should not be empty";
        int start = 1;
        String startDate = "";
        for (; start < inp.length; start++) {
            if (inp[start].equals("/from")) {
                break;
            }
        }

        for (int end = start + 1; end < inp.length; end++) {
            if (inp[end].equals("/to")) {
                break;
            }
            if (startDate.equals("")) {
                startDate = inp[end];
            } else {
                startDate += " " + inp[end];
            }
        }
        return startDate;
    }
}
