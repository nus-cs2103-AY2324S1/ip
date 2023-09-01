package duke.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectFormatException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidNumberException;

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
     * 
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
     * 
     * @param input path to be parsed.
     */
    public static String[] parseFilePath(String input) {
        int index = input.indexOf('/');
        // Returns the input string as a list of strings.
        if (index > -1) {
            return input.split("/", 2);
        } else {
            String[] tempString = { input, "" };
            return tempString;
        }
    }

    /**
     * Parses an input into an integer. This is used to determine which element in a
     * list to perform an operation on.
     * 
     * @param input string to be parsed. Must be non-null and a valid integer.
     * @return The index of the input if input is valid.
     * @throws InvalidNumberException if input isn't valid.
     */
    private static int parseIndex(String input) throws DukeException {
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
     * 
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
        String i1 = parsedInput[0].trim();
        String i2 = parsedInput[1].trim();

        // Matches both halves of the input to the date.
        if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(i1.substring(6, 10) + "-" + i1.substring(3, 5) + "-" + i1.substring(0, 2));
        } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(i2.substring(6, 10) + "-" + i2.substring(3, 5) + "-" + i2.substring(0, 2));
        } else if (i1.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(i1.substring(0, 4) + "-" + i1.substring(5, 7) + "-" + i1.substring(8, 10));
        } else if (i2.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(i2.substring(0, 4) + "-" + i2.substring(5, 7) + "-" + i2.substring(8, 10));
        } else {
            date = null;
        }

        // Matches both halves of the input to the time.
        if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":" + i1.substring(6, 8));
        } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":" + i2.substring(6, 8));
        } else if (i1.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":00");
        } else if (i2.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":00");
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
     * 
     * @param input the description of the task.
     * @param isDone true if task is completed.
     * @return a ToDoCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string is empty.
     */
    public static TodoCommand parseTodo(String input, boolean isDone) throws IncorrectFormatException {
        // Returns a new ToDoCommand object.
        if (input.equals("")) {
            throw new IncorrectFormatException();
        } else {
            return new TodoCommand(input, isDone);
        }

    }

    /**
     * Parses the input and returns a DeadlineCommand.
     * 
     * @param input the string to parse. Must be in the format
     *              "description isDone /by time/date/both".
     * @param isDone true if task is completed.
     * @return a DeadlineCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string has invalid components.
     */
    public static DeadlineCommand parseDeadline(String input, boolean isDone) throws IncorrectFormatException {
        int index = input.indexOf(" /by ");

        // Returns a new DeadlineCommand object.
        if (index > -1) {
            String[] parsedInput = input.split(" /by ", 2);

            try {
                String description = parsedInput[0];
                DateTimeWrapper dates = parseDate(parsedInput[1]);
                LocalDate byDate = dates.date;
                LocalTime byTime = dates.time;

                return new DeadlineCommand(description, isDone, byDate, byTime);
            } catch (Exception e) {
                throw new IncorrectFormatException();
            }
        } else {
            throw new IncorrectFormatException();
        }
    }

    /**
     * Parses the input string and returns an EventCommand.
     * 
     * @param input the string to parse. Must be in the format
     *              "description isDone /from time/date/both /to time/date/both".
     * @param isDone true if task is completed.
     * @return an EventCommand that can be executed on behalf of the user.
     * @throws IncorrectFormatException if input string has invalid components.
     */
    public static EventCommand parseEvent(String input, boolean isDone) throws IncorrectFormatException {
        int indexFrom = input.indexOf(" /from ");
        int indexTo = input.indexOf(" /to ");

        // Returns a new EventCommand object.
        if (indexFrom > -1 && indexTo > -1) {
            String[] parsedInput = { input.substring(0, indexFrom), input.substring(indexFrom + 7, indexTo),
                    input.substring(indexTo + 5, input.length()) };

            try {
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
        } else {
            throw new IncorrectFormatException();
        }
    }

    /**
     * Parses a file line and returns a Command. This is a helper method for loading
     * stored data into the tasklist.
     * 
     * @param input the input to be parsed.
     * @return a Command based on the input string or null if there was an error
     *         parsing the input.
     * @throws DukeException if file line has invalid format.
     */
    public static Command parseFileContent(String input) throws DukeException {
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
            throw new DukeException("Invalid file format");
        }
    }

    /**
     * Parses the input and returns a Command. This method is used to parse command
     * arguments that are sent to Duke.
     * 
     * @param input String that contains the command and arguments. Must be non-null
     *              and valid.
     * @return Command parsed from the input.
     * @throws DukeException to pass error message along to CLI.
     */
    public static Command parse(String input) throws DukeException {
        String[] parsedInput = parseInput(input);
        String command = parsedInput[0].trim();
        String args = parsedInput[1].trim();

        try {
            DukeEnum commandtype = map(command);
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
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Maps a Duke command to a Duke enum. This is used to determine which command
     * to run for each user input.
     * 
     * @param command String representation of the command.
     * @return The enum corresponding to the command or null if none is found in the
     *         enum.
     * @throws DukeException if the command is not found in the enum.
     */
    public static DukeEnum map(String command) throws DukeException {
        for (DukeEnum e : DukeEnum.values()) {
            // Returns the command that was entered by the user.
            if (command.equals(e.getText())) {
                return e;
            }
        }
        throw new InvalidCommandException();
    }
}
