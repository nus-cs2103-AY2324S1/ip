package fluke;

import fluke.exceptions.EmptyDescriptionException;
import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;
import fluke.exceptions.SaveFileParsingException;
import fluke.tasks.Deadline;
import fluke.tasks.Event;
import fluke.tasks.Task;
import fluke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles parsing for Fluke.
 */
public class Parser {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    /**
     * Parses a command (input as a string by the user) into an Enum value
     * @param command Command to be parsed
     * @return a Command corresponding to the command given.
     * @throws InvalidInputException if the command given is invalid.
     */
    public static Fluke.Command parseCommand(String command) throws InvalidInputException {
        if (command.equals("bye")) {
            return Fluke.Command.BYE;
        } else if (command.equals("list")) {
            return Fluke.Command.LIST;
        } else if (command.startsWith("mark")) {
            return Fluke.Command.MARK;
        } else if (command.startsWith("unmark")) {
            return Fluke.Command.UNMARK;
        } else if (command.startsWith("delete")) {
            return Fluke.Command.DELETE;
        } else if (command.startsWith("todo")) {
            return Fluke.Command.TODO;
        } else if (command.startsWith("deadline")) {
            return Fluke.Command.DEADLINE;
        } else if (command.startsWith("event")) {
            return Fluke.Command.EVENT;
        } else if (nextCommand.startsWith("find")) {
            return Fluke.Command.FIND;
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the task from a line in the save file corresponding to a task.
     * @param taskString Line in the save file
     * @return a Task corresponding to the line.
     * @throws FlukeException if the line is invalid, for instance if the save file is tampered with or corrupted.
     */
    public static Task parseTask(String taskString) throws FlukeException {
        Fluke.Command taskType;
        boolean isMarked;
        // parse type
        Pattern typePattern = Pattern.compile("\\[[TDE]]");
        Matcher typeMatcher = typePattern.matcher(taskString);
        boolean typeIsFound = typeMatcher.find();
        if (!typeIsFound) {
            throw new SaveFileParsingException();
        }
        switch (typeMatcher.group()) {
        case "[T]":
            taskType = Fluke.Command.TODO;
            break;
        case "[D]":
            taskType = Fluke.Command.DEADLINE;
            break;
        case "[E]":
            taskType = Fluke.Command.EVENT;
            break;
        default:
            throw new SaveFileParsingException();
        }

        // parse mark
        Pattern markPattern = Pattern.compile("\\[[X ]]");
        Matcher markMatcher = markPattern.matcher(taskString);
        boolean markIsFound = markMatcher.find();
        if (!markIsFound) {
            throw new SaveFileParsingException();
        }
        String mark = markMatcher.group();
        switch (mark) {
        case "[ ]":
            isMarked = false;
            break;
        case "[X]":
            isMarked = true;
            break;
        default:
            throw new SaveFileParsingException();
        }

        String taskDesc = taskString.substring(7);
        if (taskType == Fluke.Command.TODO) {
            return new Todo(taskDesc, isMarked);
        } else if (taskType == Fluke.Command.DEADLINE) {
            // parse by date
            int bracketStartIndex = taskDesc.indexOf('(');
            int bracketEndIndex = taskDesc.indexOf(')');
            if (bracketStartIndex < 0 || bracketEndIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            String by = taskDesc.substring(bracketStartIndex + 4, bracketEndIndex).trim();
            LocalDate date = LocalDate.parse(by, DATE_TIME_FORMATTER);
            return new Deadline(desc, isMarked, date.toString());
        } else if (taskType == Fluke.Command.EVENT) {
            int bracketStartIndex = taskDesc.indexOf('(');
            if (bracketStartIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            // parse from date
            Pattern fromPattern = Pattern.compile("from:.+to:");
            Matcher fromMatcher = fromPattern.matcher(taskDesc);
            boolean fromDateIsFound = fromMatcher.find();
            if (!fromDateIsFound) {
                throw new SaveFileParsingException();
            }
            String from = fromMatcher.group().substring(5).replaceFirst("to:", "").trim();
            LocalDate fromDate = LocalDate.parse(from, DATE_TIME_FORMATTER);
            // parse to date
            Pattern toPattern = Pattern.compile("to:.+\\)");
            Matcher toMatcher = toPattern.matcher(taskDesc);
            boolean toDateIsFound = toMatcher.find();
            if (!toDateIsFound) {
                throw new SaveFileParsingException();
            }
            String to = toMatcher.group().substring(3).replaceFirst("\\)", "").trim();
            LocalDate toDate = LocalDate.parse(to, DATE_TIME_FORMATTER);
            return new Event(desc, isMarked, fromDate.toString(), toDate.toString());
        }
        throw new SaveFileParsingException();
    }

    /**
     * Parses a Todo command.
     * @param command Command given by the user
     * @return the description of the todo task.
     * @throws FlukeException if the description given is empty.
     */
    public static String parseTodoCommand(String command) throws FlukeException {
        if (command.length() <= 5) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        return command.substring(5);
    }

    /**
     * Parses and validates a Deadline command
     * @param command Command given by the user.
     * @return a String array containing at 0: description, 1: (String) by date
     * @throws FlukeException if the description given is invalid, or if the by date given is invalid.
     */
    public static String[] parseDeadlineCommand(String command) throws FlukeException {
        if (command.length() <= 9) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        String str = command.substring(9);
        int byIndex = str.indexOf("/by");
        if (byIndex < 0) {
            throw new InvalidInputException();
        }
        String description = str.substring(0, byIndex - 1);
        String by = str.substring(byIndex + 4);
        return new String[]{description, by};
    }

    /**
     * Parses and validates an Event command.
     * @param command Command given by the user.
     * @return a String array containing at 0: description, 1: (String) from date 2: to date.
     * @throws FlukeException if any of the inputs are invalid.
     */
    public static String[] parseEventCommand(String command) throws FlukeException {
        if (command.length() <= 6) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        String str = command.substring(6);
        int fromIndex = str.indexOf("/from");
        int toIndex = str.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            throw new InvalidInputException();
        }
        String description = str.substring(0, fromIndex - 1);
        String from = str.substring(fromIndex + 6, toIndex - 1);
        String to = str.substring(toIndex + 4);
        return new String[]{description, from, to};
    }

    /**
     * Parses a Delete command.
     * @param command Command given by the user.
     * @return index of the task in the task list.
     * @throws FlukeException if the input is invalid.
     */
    public static int parseDeleteCommand(String command) throws FlukeException {
        if (command.length() <= 7) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(command.substring(7));
        return taskNumber - 1;
    }

    /**
     * Parses a Mark as Done Command.
     * @param command Command given by the user.
     * @return index of the task in the task list to be marked.
     * @throws FlukeException if the input is invalid.
     */
    public static int parseMarkAsDoneCommand(String command) throws FlukeException {
        if (command.length() <= 5) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(command.substring(5));
        return taskNumber - 1;
    }

    /**
     * Parses a Mark as Undone Command.
     * @param command Command given by the user.
     * @return index of the task in the task list to be marked.
     * @throws FlukeException if the input is invalid.
     */
    public static int parseMarkAsUndoneCommand(String command) throws FlukeException {
        if (command.length() <= 7) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(command.substring(7));
        return taskNumber - 1;
    }

    /**
     * Helper function to obtain a number from a string.
     * @param taskNumberString String to obtain a number from.
     * @return An integer
     * @throws InvalidInputException if the string is not a valid number.
     */
    private static int obtainTaskNumber(String taskNumberString) throws InvalidInputException {
        try {
            return Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses a find command to obtain the keyword
     * @param command the command given.
     * @return the keyword
     * @throws InvalidInputException if there is no keyword given.
     */
    public static String parseFindCommand(String command) throws InvalidInputException {
        if (command.length() < 6) {
            throw new InvalidInputException();
        }
        return command.substring(5);
    }
}
