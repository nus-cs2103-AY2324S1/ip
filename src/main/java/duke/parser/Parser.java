package duke.parser;

import duke.commands.Command;
import duke.commands.CommandType;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String userInput) {
        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0];
        String commandDetails = userInputArray.length > 1 ? userInputArray[1] : "";
        CommandType commandType = CommandType.getCommandType(command);

        switch (commandType) {
        case BYE:
            return new Command.ExitCommand();
        case LIST:
            return new Command.ListCommand();
        case MARK:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! Please enter a task number to mark as done.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.MarkCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("☹ OOPS!!! Please enter a valid task number to mark as done.");
                }
            }
        case UNMARK:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! Please enter a task number to unmark.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.UnmarkCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("☹ OOPS!!! Please enter a valid task number to unmark.");
                }
            }
        case DELETE:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! Please enter a task number to delete.");
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandDetails);
                    return new Command.DeleteCommand(taskNumber);
                } catch (NumberFormatException e) {
                    return new Command.InvalidCommand("☹ OOPS!!! Please enter a valid task number to delete.");
                }
            }
        case ADD_TODO:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new Command.AddCommand(new ToDoTask(commandDetails), CommandType.ADD_TODO);
            }
        case ADD_DEADLINE:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (!commandDetails.contains("/by")) {
                return new Command.InvalidCommand("☹ OOPS!!! Please enter a deadline in the format: deadline <task> /by <date> <time>");
            } else {
                String[] taskDetailsArray = commandDetails.split("/by");
                if (taskDetailsArray.length < 2) {
                    return new Command.InvalidCommand("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                }
                String taskName = taskDetailsArray[0].trim();
                String stringDeadline = taskDetailsArray[1].trim();

                try {
                    LocalDateTime deadline = LocalDateTime.parse(stringDeadline, Ui.DATE_FORMAT_INPUT);
                    return new Command.AddCommand(new DeadlineTask(taskName, deadline), CommandType.ADD_DEADLINE);
                } catch (DateTimeParseException e) {
                    return new Command.InvalidCommand("☹ OOPS!!! Please enter a valid date and time in the format: dd/MM/yyyy HHmm");
                }
            }
        case ADD_EVENT:
            if (commandDetails.equals("")) {
                return new Command.InvalidCommand("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (!commandDetails.contains("/from") || !commandDetails.contains("/to")) {
                return new Command.InvalidCommand("☹ OOPS!!! Please enter an event in the format: event <task> /from <date> <time> /to <date> <time>");
            } else {
                String[] taskDetailsArray = commandDetails.split("/from");
                if (taskDetailsArray.length < 2) {
                    return new Command.InvalidCommand("☹ OOPS!!! The start time of an event cannot be empty.");
                }
                String taskName = taskDetailsArray[0].trim();
                String[] taskDetailsArray2 = taskDetailsArray[1].split("/to");
                if (taskDetailsArray2.length < 2) {
                    return new Command.InvalidCommand("☹ OOPS!!! The end time of an event cannot be empty.");
                }

                String stringStartTime = taskDetailsArray2[0].trim();
                String stringEndTime = taskDetailsArray2[1].trim();
                try {
                    LocalDateTime startTime = LocalDateTime.parse(stringStartTime, Ui.DATE_FORMAT_INPUT);
                    LocalDateTime endTime = LocalDateTime.parse(stringEndTime, Ui.DATE_FORMAT_INPUT);
                    return new Command.AddCommand(new EventTask(taskName, startTime, endTime), CommandType.ADD_EVENT);
                } catch (DateTimeParseException e) {
                    return new Command.InvalidCommand("☹ OOPS!!! Please enter a valid date and time in the format: dd/MM/yyyy HHmm");
                }
            }
        case INVALID:
            return new Command.InvalidCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return new Command.InvalidCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
