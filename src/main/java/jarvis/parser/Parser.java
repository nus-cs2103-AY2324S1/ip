package jarvis.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jarvis.commands.ByeCommand;
import jarvis.commands.Command;
import jarvis.commands.DeadlineCommand;
import jarvis.commands.DeleteCommand;
import jarvis.commands.EventCommand;
import jarvis.commands.FindCommand;
import jarvis.commands.HelpCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.SortDeadlineCommand;
import jarvis.commands.TodoCommand;
import jarvis.commands.UnmarkCommand;
import jarvis.exceptions.InvalidCommandException;
import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.ui.Ui;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;

/**
 * The Parser class is responsible for parsing user input and converting it into executable commands and
 * tasks.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The user's input.
     * @return The corresponding command.
     * @throws InvalidCommandException If the input is an invalid command.
     */
    public static Command parseCommand(final String userInput) throws InvalidCommandException {
        String[] splitUserInput = userInput.split(" ");
        return generateCommand(userInput, splitUserInput);
    }

    private static Command generateCommand(String userInput, String[] splitUserInput)
            throws InvalidCommandException {
        assert userInput != null && !userInput.isEmpty() : "User input should not be null or empty";
        if (userInput.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (userInput.equalsIgnoreCase("sort deadline")) {
            return new SortDeadlineCommand();
        } else if (splitUserInput[0].startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("delete")) {
            return new DeleteCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("find")) {
            return new FindCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("todo")) {
            return new TodoCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(userInput);
        } else if (splitUserInput[0].equalsIgnoreCase("event")) {
            return new EventCommand(userInput);
        } else {
            throw new InvalidCommandException(null);
        }
    }

    /**
     * Parses a string representation of a task into a Task object.
     *
     * @param line The string representation of the task.
     * @return A Task object representing the parsed task.
     * @throws InvalidTaskFormatException If the task format is invalid.
     */
    public static Task parseStringToTask(final String line) throws InvalidTaskFormatException {
        try {
            String[] splitLine = line.split("\\|");
            String taskType = splitLine[0].trim();
            boolean isCompleted = Integer.parseInt(splitLine[1].trim()) == 1;
            String taskDetails = splitLine[2].trim();
            assert !taskDetails.isEmpty() : "Task should have description " + taskDetails;

            switch (taskType) {
            case "T":
                return new Todo(taskDetails, isCompleted);
            case "D":
                String deadlineByString = splitLine[3].trim();
                LocalDateTime formattedDeadlineBy = parseStringToDateTime(deadlineByString);
                return new Deadline(taskDetails, formattedDeadlineBy, isCompleted);
            case "E":
                String fromTime = splitLine[3].trim();
                String toTime = splitLine[4].trim();
                LocalDateTime formattedFromTime = parseStringToDateTime(fromTime);
                LocalDateTime formattedToTime = parseStringToDateTime(toTime);
                return new Event(taskDetails, formattedFromTime, formattedToTime, isCompleted);
            default:
                throw new InvalidTaskFormatException("Unknown Task Type: " + taskType);
            }
        } catch (Exception e) {
            throw new InvalidTaskFormatException("Invalid task format: " + line);
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param inputDateTime The date and time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws InvalidDateTimeFormatException If the input date and time format is invalid.
     */
    public static LocalDateTime parseStringToDateTime(final String inputDateTime)
            throws InvalidDateTimeFormatException {
        List<String> inputFormats = getInputFormats();
        return generateLocalDateTime(inputDateTime, inputFormats);
    }

    private static List<String> getInputFormats() {
        List<String> inputFormats = new ArrayList<>();
        inputFormats.add(Ui.getDefaultDateTimeFormat());
        inputFormats.add("dd MMM yyyy HHmm");
        inputFormats.add("yyyy-MM-dd HHmm");
        inputFormats.add("dd/MM/yyyy HHmm");
        return inputFormats;
    }

    private static LocalDateTime generateLocalDateTime(String inputDateTime, List<String> inputFormats)
            throws InvalidDateTimeFormatException {
        LocalDateTime result = null;
        for (String inputFormat : inputFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat);
                result = LocalDateTime.parse(inputDateTime, formatter);
                break;
            } catch (Exception e) {
                continue;
            }
        }

        if (result != null) {
            return result;
        } else {
            throw new InvalidDateTimeFormatException(inputDateTime);
        }
    }
}
