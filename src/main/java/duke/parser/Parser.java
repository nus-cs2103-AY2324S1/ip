package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.CommandType;
import duke.exception.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task.Priority;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

/**
 * The Parser class is responsible for parsing user input commands and converting them into executable commands.
 * It supports commands such as adding tasks, marking tasks as done, deleting tasks, and more.
 */
public class Parser {

    /**
     * Parses the user input and returns an appropriate Command object based on the input.
     *
     * @param userInput The user's input command as a string.
     * @return A command object that corresponds to the parsed input.
     */
    public static Command parse(String userInput) {
        assert userInput != null : "User input cannot be null";

        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0];
        String commandDetails = userInputArray.length > 1 ? userInputArray[1] : "";
        CommandType commandType = CommandType.getCommandType(command);
        assert commandType != null : "Command type cannot be null";

        switch (commandType) {
        case BYE:
            return new Command.ExitCommand();
        case LIST:
            return new Command.ListCommand();
        case MARK:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a task number to mark as done.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.MarkCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("OOPS!!! Please enter a valid task number to mark as done.");
                }
            }
        case UNMARK:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a task number to unmark.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.UnmarkCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("OOPS!!! Please enter a valid task number to unmark.");
                }
            }
        case PRIORITY:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a task number and a new priority"
                        + " to change the priority.");
            }

            String[] priorityInfo = commandDetails.split(" ", 2);
            if (priorityInfo.length < 2) {
                return new Command.InvalidCommand("OOPS!!! Please provide both a task number and a new priority.");
            }

            try {
                int taskNumber = Integer.parseInt(priorityInfo[0]);
                String newPriorityValue = priorityInfo[1].trim();

                Priority newPriority;
                if (newPriorityValue.equalsIgnoreCase("1")
                        || newPriorityValue.equalsIgnoreCase("high")) {
                    newPriority = Priority.HIGH;
                } else if (newPriorityValue.equalsIgnoreCase("2")
                        || newPriorityValue.equalsIgnoreCase("medium")) {
                    newPriority = Priority.MEDIUM;
                } else if (newPriorityValue.equalsIgnoreCase("3")
                        || newPriorityValue.equalsIgnoreCase("low")) {
                    newPriority = Priority.LOW;
                } else {
                    return new Command.InvalidCommand("OOPS!!! Invalid new priority."
                            + " Please use '1', '2', '3', 'high', 'medium', or 'low'.");
                }

                return new Command.PriorityCommand(taskNumber, newPriority);
            } catch (NumberFormatException e) {
                return new Command.InvalidCommand("OOPS!!! Please enter a valid task number to change the priority.");
            }
        case DELETE:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a task number to delete.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.DeleteCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("OOPS!!! Please enter a valid task number to delete.");
                }
            }
        case ADD_TODO:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! The description of a todo cannot be empty.");
            }

            try {
                String[] taskDetailsArray = commandDetails.split("/p", 2);
                String taskName = taskDetailsArray[0].trim();
                Priority priority = parsePriorityFromCommandDetails(commandDetails);
                return new Command.AddCommand(new ToDoTask(taskName, priority), CommandType.ADD_TODO);
            } catch (DukeException e) {
                return new Command.InvalidCommand(e.getMessage());
            }
        case ADD_DEADLINE:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! The description of a deadline cannot be empty.");
            }

            if (!commandDetails.contains("/by")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a deadline in the format: "
                        + "deadline <task> /by <date> <time> /p <priority>");
            }

            try {
                String[] taskDetailsArray = commandDetails.split("/by");
                if (taskDetailsArray.length < 2) {
                    return new Command.InvalidCommand("OOPS!!! The deadline of a deadline task cannot be empty.");
                }

                String taskName = taskDetailsArray[0].trim();
                String stringDeadline = taskDetailsArray[1].split("/p")[0].trim();
                LocalDateTime deadline = LocalDateTime.parse(stringDeadline, Ui.DATE_FORMAT_INPUT);
                Priority priority = parsePriorityFromCommandDetails(commandDetails);

                return new Command.AddCommand(new DeadlineTask(taskName, deadline, priority), CommandType.ADD_DEADLINE);
            } catch (DateTimeParseException e) {
                return new Command.InvalidCommand("OOPS!!! Please enter a valid date and time in the format: "
                        + "dd/MM/yyyy HHmm");
            } catch (DukeException e) {
                return new Command.InvalidCommand(e.getMessage());
            }
        case ADD_EVENT:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! The description of an event cannot be empty.");
            }

            if (!commandDetails.contains("/from") || !commandDetails.contains("/to")) {
                return new Command.InvalidCommand("OOPS!!! Please enter an event in the format: "
                        + "event <task> /from <date> <time> /to <date> <time> /p <priority>");
            }

            try {
                String[] taskDetailsArray = commandDetails.split("/from");
                if (taskDetailsArray.length < 2) {
                    return new Command.InvalidCommand("OOPS!!! The start time of an event cannot be empty.");
                }

                String[] taskDetailsArray2 = taskDetailsArray[1].split("/to");
                if (taskDetailsArray2.length < 2) {
                    return new Command.InvalidCommand("OOPS!!! The end time of an event cannot be empty.");
                }

                String taskName = taskDetailsArray[0].trim();
                String stringStartTime = taskDetailsArray2[0].trim();
                String stringEndTime = taskDetailsArray2[1].split("/p")[0].trim();
                LocalDateTime startTime = LocalDateTime.parse(stringStartTime, Ui.DATE_FORMAT_INPUT);
                LocalDateTime endTime = LocalDateTime.parse(stringEndTime, Ui.DATE_FORMAT_INPUT);
                Priority priority = parsePriorityFromCommandDetails(commandDetails);

                return new Command.AddCommand(new EventTask(taskName, startTime, endTime, priority),
                        CommandType.ADD_EVENT);

            } catch (DateTimeParseException e) {
                return new Command.InvalidCommand("OOPS!!! Please enter a valid date and time in the format: "
                        + "dd/MM/yyyy HHmm");
            } catch (DukeException e) {
                return new Command.InvalidCommand(e.getMessage());
            }
        case FIND:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("OOPS!!! Please enter a keyword to search for.");
            } else {
                return new Command.Find(commandDetails);
            }
        case INVALID:
            return new Command.InvalidCommand("OOPS!!! I'm sorry, but I don't know what that means :-(");
        default:
            assert false : "There is a command type in duke.commands.CommandType that is not explicitly handled here";
            return new Command.InvalidCommand("There is a command type in duke.commands.CommandType"
                    + " that is not explicitly handled here.");
        }
    }

    /**
     * Parses the priority from the command details.
     * If no priority is provided, the default priority is low.
     *
     * @param input
     * @return The priority of the task.
     * @throws DukeException
     */
    private static Priority parsePriorityFromCommandDetails(String input) throws DukeException {
        if (!input.contains("/p")) {
            return Priority.LOW; // Default to low if /p is not provided
        }

        String[] parts = input.split("/p", 2);
        String priorityValue = parts[1].trim();

        // Determine priority based on user input
        if (priorityValue.equalsIgnoreCase("1")
                || priorityValue.equalsIgnoreCase("high")) {
            return Priority.HIGH;
        } else if (priorityValue.equalsIgnoreCase("2")
                || priorityValue.equalsIgnoreCase("medium")) {
            return Priority.MEDIUM;
        } else if (priorityValue.equalsIgnoreCase("3")
                || priorityValue.equalsIgnoreCase("low")
                || priorityValue.equalsIgnoreCase("")) {
            return Priority.LOW;
        } else {
            throw new DukeException("OOPS!!! Please enter a valid priority level: "
                    + "1 (high), 2 (medium), 3 (low).\n"
                    + "If no priority level is specified, it will default to low.");
        }
    }
}
