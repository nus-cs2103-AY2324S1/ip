package chat.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import chat.commands.ByeCommand;
import chat.commands.Command;
import chat.commands.DeadlineCommand;
import chat.commands.DeleteCommand;
import chat.commands.EventCommand;
import chat.commands.FindCommand;
import chat.commands.ListCommand;
import chat.commands.MarkDoneCommand;
import chat.commands.MemeCommand;
import chat.commands.SortCommand;
import chat.commands.TodoCommand;
import chat.commands.SortCommand.sortType;
import chat.exceptions.ChatException;
import chat.exceptions.IncorrectFileFormatException;
import chat.exceptions.IncorrectFormatException;
import chat.exceptions.InvalidCommandException;
import chat.exceptions.InvalidNumberException;

/**
 * Handles all command parsing capabilities used by Chat.
 * @author juzzztinsoong
 */
public class Parser {

    public Parser() {
    };

    private static class DateTimeWrapper {
        private LocalDate date;
        private LocalTime time;

        private DateTimeWrapper(LocalDate date, LocalTime time) {
            this.date = date;
            this.time = time;
        }
    }

    /**
     * Parses the input and returns the command and its arguments as an array of
     * Strings.
     * If there is only one word in the input it returns an array of the input and
     * an empty string.
     * @param input the command given by the user.
     */
    private static String[] parseInput(String input) {
        int index = input.indexOf(' ');
        // Returns the input string as a list of strings.
        if (index > -1) {
            return input.split(" ", 2);
        } else {
            String[] tempString = { input, "" };
            return tempString;
        }
    }

    /**
     * Parses file path and returns array of Strings representing each level in the
     * file path.
     * @param input path to be parsed.
     */
    public static String[] parseFilePath(String input) {
        int index = input.indexOf('/');
        // Returns the input string as a list of strings.
        if (index > -1) {
            return input.split("/", 10);
        } else {
            String[] tempString = { input, "" };
            return tempString;
        }
    }

    /**
     * Parses an input into an integer. This is used to determine which element in a
     * list to perform an operation on.
     * @param input string to be parsed. Must be non-null and a valid integer.
     * @return The index of the input if input is valid.
     * @throws InvalidNumberException if input isn't valid.
     */
    private static int parseIndex(String input) throws ChatException {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }

    /**
     * Parses the input and returns a DateTimeWrapper. This is a convenience method
     * for deadlineParser and eventParser that will try to parse the date and time
     * out of the input.
     * @param input the input to parse. Must be non-null and a valid date or time or
     *              both.
     * @return a DateTimeWrapper that contains the date and time of the input if
     *         applicable.
     * @throws IncorrectFormatException if input isn't valid.
     */
    private static DateTimeWrapper parseDate(String input) throws IncorrectFormatException {
        // Input is parsed into two if it contains both a date and time component.
        String[] parsedInput = parseInput(input);
        LocalDate date;
        LocalTime time;
        String s1 = parsedInput[0].trim();
        String s2 = parsedInput[1].trim();

        // Matches both halves of the input to the date.
        if (s1.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(s1.substring(6, 10) + "-" + s1.substring(3, 5) + "-" + s1.substring(0, 2));
        } else if (s2.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(s2.substring(6, 10) + "-" + s2.substring(3, 5) + "-" + s2.substring(0, 2));
        } else if (s1.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(s1.substring(0, 4) + "-" + s1.substring(5, 7) + "-" + s1.substring(8, 10));
        } else if (s2.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(s2.substring(0, 4) + "-" + s2.substring(5, 7) + "-" + s2.substring(8, 10));
        } else {
            date = null;
        }

        // Matches both halves of the input to the time.
        if (s1.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(s1.substring(0, 2) + ":" + s1.substring(3, 5) + ":" + s1.substring(6, 8));
        } else if (s2.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(s2.substring(0, 2) + ":" + s2.substring(3, 5) + ":" + s2.substring(6, 8));
        } else if (s1.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(s1.substring(0, 2) + ":" + s1.substring(3, 5) + ":00");
        } else if (s2.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(s2.substring(0, 2) + ":" + s2.substring(3, 5) + ":00");
        } else {
            time = null;
        }

        // Checks that the time and date fields are not null.
        if (time == null && date == null) {
            throw new IncorrectFormatException();
        }

        return new DateTimeWrapper(date, time);
    }

    /**
     * Parses a string and returns a ToDoCommand. If the string is empty an
     * IncorrectFormatException is thrown.
     * @param input the description of the task.
     * @param isDone true if task is completed.
     * @return a ToDoCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string is empty.
     */
    private static TodoCommand parseTodo(String input, boolean isDone) throws IncorrectFormatException {
        // Returns a new ToDoCommand object.
        if (input.equals("")) {
            throw new IncorrectFormatException();
        }
        return new TodoCommand(input, isDone);
    }

    /**
     * Parses the input and returns a DeadlineCommand.
     * @param input the string to parse. Must be in the format
     *              "description isDone /by time/date/both".
     * @param isDone true if task is completed.
     * @return a DeadlineCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string has invalid components.
     */
    private static DeadlineCommand parseDeadline(String input, boolean isDone) throws IncorrectFormatException {
        int index = input.indexOf(" /by ");

        if (index < 0) {
            throw new IncorrectFormatException();
        }
        
        // Returns a new DeadlineCommand object.
        try {
            String[] parsedInput = input.split(" /by ", 2);
            String description = parsedInput[0];
            DateTimeWrapper dates = parseDate(parsedInput[1]);
            LocalDate byDate = dates.date;
            LocalTime byTime = dates.time;

            return new DeadlineCommand(description, isDone, byDate, byTime);
        } catch (Exception e) {
            throw new IncorrectFormatException();
        }
    }

    /**
     * Parses the input string and returns an EventCommand.
     * @param input the string to parse. Must be in the format
     *              "description isDone /from time/date/both /to time/date/both".
     * @param isDone true if task is completed.
     * @return an EventCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string has invalid components.
     */
    private static EventCommand parseEvent(String input, boolean isDone) throws IncorrectFormatException {
        int indexFrom = input.indexOf(" /from ");
        int indexTo = input.indexOf(" /to ");

        if (indexFrom < 0 || indexTo < 0) {
            throw new IncorrectFormatException();
        }

        // Returns a new EventCommand object.
        try {
            String[] parsedInput = { input.substring(0, indexFrom), input.substring(indexFrom + 7, indexTo),
                input.substring(indexTo + 5, input.length()) };
            String description = parsedInput[0];
            DateTimeWrapper fromDateTime = parseDate(parsedInput[1]);
            DateTimeWrapper toDateTime = parseDate(parsedInput[2]);
            LocalDate fromDate = fromDateTime.date;
            LocalTime fromTime = fromDateTime.time;
            LocalDate toDate = toDateTime.date;
            LocalTime toTime = toDateTime.time;

            // Smart date guesser for incomplete date formats e.g.
            // 1/1/2023 12:00 to 16:00 will be assumed to be 1/1/2023 12:00 to 1/1/2023 16:00.
            if (toDate == null && fromDate != null) {
                toDate = fromDate;
            }
            if (toDate != null && fromDate == null) {
                fromDate = toDate;
            }
            if (fromTime == null && toTime != null) {
                fromTime = toTime;
            }
            if (fromTime != null && toTime == null) {
                toTime = fromTime;
            }

            return new EventCommand(description, isDone, fromDate, fromTime, toDate, toTime);
        } catch (Exception e) {
            throw new IncorrectFormatException();
        } 
    }

    /**
     * Parses sort command and calls the sort command of the right type.
     * @param input the input to be parsed.
     * @return a Sort Command of the right type i.e. sorting by name, date or type.
     * @throws IncorrectFormatException if the input is not of any programmed type.
     */
    private static SortCommand parseSort(String input) throws IncorrectFormatException {
        input.trim();
        if (input.contains("name")) {
            return new SortCommand(sortType.NAME);
        } else if (input.contains("date")) {
            return new SortCommand(sortType.DATE);
        } else if (input.contains("type")) {
            return new SortCommand(sortType.TYPE);
        } else {
            throw new IncorrectFormatException();
        }
    }

    /**
     * Parses a file line and returns a Command. This is a helper method for loading
     * stored data into the tasklist.
     * @param input the input to be parsed.
     * @return a Command based on the input string or null if there was an error
     *         parsing the input.
     * @throws ChatException if file line has invalid format.
     */
    public static Command parseFileContent(String input) throws IncorrectFileFormatException {
        try {
            String[] parsedContent = input.split(" # ");
            boolean isDone = Integer.parseInt(parsedContent[1]) == 1;
            if (parsedContent.length == 3 && parsedContent[0].charAt(0) == 'T') {
                // Parse as Todo.
                return parseTodo(parsedContent[2], isDone);
            } else if (parsedContent.length == 4 && parsedContent[0].charAt(0) == 'D') {
                // Parse as Deadline.
                return parseDeadline(parsedContent[2] + " /by " + parsedContent[3], isDone);
            } else if (parsedContent.length == 5 && parsedContent[0].charAt(0) == 'E') {
                // Parse as Event.
                return parseEvent(parsedContent[2] + " /from " + parsedContent[3] + " /to " + parsedContent[4], isDone);
            } else {
                throw new IncorrectFileFormatException();
            }
        } catch (IncorrectFormatException e) {
            throw new IncorrectFileFormatException();
        } catch (Exception e) {
            throw new IncorrectFileFormatException();
        }
    }

    /**
     * Parses the input and returns a Command. This method is used to parse command
     * arguments that are sent to Chat.
     * @param input String that contains the command and arguments. Must be non-null
     *              and valid.
     * @return Command parsed from the input.
     * @throws ChatException to pass error message along to CLI.
     */
    public static Command parse(String input) throws ChatException {
        assert input != null : "Input should not be null";
        
        String[] parsedInput = parseInput(input);
        String command = parsedInput[0].trim();
        String args = parsedInput[1].trim();
        Enum commandtype = map(command);

        // Returns a command object for the command type.
        switch (commandtype) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkDoneCommand(true, parseIndex(args));
        case UNMARK:
            return new MarkDoneCommand(false, parseIndex(args));
        case TODO:
            return parseTodo(args, false);
        case DEADLINE:
            return parseDeadline(args, false);
        case EVENT:
            return parseEvent(args, false);
        case DELETE:
            return new DeleteCommand(parseIndex(args));
        case FIND:
            return new FindCommand(args);
        case SORT:
            return parseSort(args);
        case MEME:
            return new MemeCommand();
        default:
            throw new InvalidCommandException();
        } 
    }

    /**
     * Maps a Chat command to an enum. This is used to determine which command
     * to run for each user input.
     * @param command String representation of the command.
     * @return The enum corresponding to the command or null if none is found in the
     *         enum.
     * @throws ChatException if the command is not found in the enum.
     */
    protected static Enum map(String command) throws ChatException {
        for (Enum e : Enum.values()) {
            // Returns the command that was entered by the user.
            if (command.equals(e.getText())) {
                return e;
            }
        }
        throw new InvalidCommandException();
    }
}
