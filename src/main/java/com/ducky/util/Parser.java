package com.ducky.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.ducky.command.Command;
import com.ducky.command.DeleteCommand;
import com.ducky.command.DuckyInvalidCommandException;
import com.ducky.command.DuckyInvalidCommandFormatException;
import com.ducky.command.FindTaskCommand;
import com.ducky.command.ListCommand;
import com.ducky.command.ViewScheduleCommand;
import com.ducky.task.DeadlineTask;
import com.ducky.task.EventTask;
import com.ducky.task.Task;
import com.ducky.task.TodoTask;

/**
 * Represents a Parser used for parsing commands and dates.
 */
public class Parser {


    /**
     * Constructs a Parser instance.
     */
    public Parser() {}

    /**
     * Parses the specified input and returns its Command representation, if applicable.
     *
     * @param cmd Command to be parsed.
     * @return Command representation of specified input.
     * @throws DuckyInvalidCommandException If the command does not exist.
     * @throws DuckyInvalidCommandFormatException If the command is not in the right format.
     */
    public static Command parse(String cmd) throws DuckyInvalidCommandException, DuckyInvalidCommandFormatException {
        String[] parts = cmd.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String argumentString = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandType) {
        case "list":
            return new ListCommand();
        case "find":
            return new FindTaskCommand(argumentString);
        case "schedule":
            return new ViewScheduleCommand(argumentString);
        case "mark":
            return ParsedCommandHandler.handleUpdateTaskCompletion(argumentString, true);
        case "unmark":
            return ParsedCommandHandler.handleUpdateTaskCompletion(argumentString, false);
        case "delete":
            return new DeleteCommand(Integer.parseInt(argumentString));
        case "todo":
            return ParsedCommandHandler.handleAddTodoTask(argumentString);
        case "deadline":
            return ParsedCommandHandler.handleAddDeadlineTask(argumentString);
        case "event":
            return ParsedCommandHandler.handleAddEventTask(argumentString);
        default:
            throw new DuckyInvalidCommandException();
        }
    }

    /**
     * Parses the specified date given as a string (yyyy-mm-dd format)
     * and returns it as LocalDate.
     *
     * @param date String representation of date to be parsed.
     * @return LocalDate representation of specified date.
     * @throws DateTimeParseException If the input date format is not valid.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
    }

    /**
     * Parses the given Task string (in saved format) and returns it as a Task.
     *
     * @param line Task string in saved format
     * @return Task representation of the saved task.
     * @throws DateTimeParseException If the task includes an invalid date format.
     * @throws DuckyFileParseException If the string is detected as corrupted.
     */
    public static Task parseSavedTask(String line) throws DateTimeParseException, DuckyFileParseException {
        String[] lineParts = line.trim().split(" \\| ");
        if (lineParts.length < 3) {
            throw new DuckyFileParseException();
        }
        boolean taskIsDone = lineParts[1].equals("1");

        Task parsedTask;

        switch (lineParts[0]) {
        case "T":
            parsedTask = new TodoTask(lineParts[2]);
            break;
        case "D":
            LocalDate deadline = Parser.parseDate(lineParts[3]);
            parsedTask = new DeadlineTask(lineParts[2], deadline);
            break;
        case "E":
            parsedTask = new EventTask(lineParts[2], lineParts[3], lineParts[4]);
            break;
        default:
            throw new DuckyFileParseException();
        }

        if (taskIsDone) {
            parsedTask.setComplete();
        }

        return parsedTask;
    }
}
