package duke.parsers;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddTaskCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.MarkAsDoneCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


public class ParserHelper {
    private static final Pattern emptyStringChecker = Pattern.compile("\\S.*+");
    // Use regular expression to check if the input is a number
    private static final Pattern numberChecker = Pattern.compile("\\d+?");

    private static String[] splitString(String information) {
        return information.split(" ", 2);
    }


    private static int getIndex(String index) {
        try {
            //parse the input string as an integer and subtract 1 to convert it to zero-based index
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            //if the input is not a valid integer, return -1
            return -1;
        }
    }


    public static MarkAsDoneCommand parseMarkCommand(String information) throws UnknownCommandException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new MarkAsDoneCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new UnknownCommandException(ErrorMessages.INVALID_TASK_INDEX_ERROR);
        }
    }

    public static UnmarkCommand parseUnmarkCommand(String information) throws UnknownCommandException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new UnmarkCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new UnknownCommandException(ErrorMessages.INVALID_TASK_INDEX_ERROR);
        }
    }

    public static DeleteCommand parseDeleteCommand(String information) throws UnknownCommandException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new DeleteCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new UnknownCommandException(ErrorMessages.INVALID_TASK_INDEX_ERROR);
        }
    }

    public static FindCommand parseFindCommand(String information) throws UnknownCommandException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_DESCRIPTION_ERROR);
        } else {
            String[] descriptions = information.split(" ");
            // create a new FindCommand with the array of descriptions
            return new FindCommand(descriptions);
        }
    }

    public static HelpCommand parseHelpCommand(String information) throws UnknownCommandException {
        if (!emptyStringChecker.matcher(information).matches()) {
            // If the information is an empty string, return a help command with the normal mode
            return new HelpCommand("normal");
        } else {
            // If the information is not an empty string, return a help command with the information as its mode
            return new HelpCommand(information);
        }
    }

    public static AddTaskCommand parseTodoCommand(String information) throws UnknownCommandException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_TODO_ERROR);
        } else {
            // create a new TodoTask and return an AddTaskCommand with it
            return new AddTaskCommand(new Todo(information));
        }
    }

    public static AddTaskCommand parseDeadlineCommand(String information) throws UnknownCommandException {
        // Check if the input string is empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_DEADLINE_ERROR);
        }

        // Extract the name and deadline date from the input string
        Matcher dateChecker = extractNameAndDate(information);

        // If the input string is in the correct format
        if (dateChecker.matches()) {
            // Create an `AddTaskCommand` for a deadline task
            return createDeadlineTaskCommand(dateChecker);
        } else {
            // If the input string is in an incorrect format, throw an exception
            throw new UnknownCommandException(ErrorMessages.INVALID_DEADLINE_FORMAT_ERROR);
        }
    }

    private static Matcher extractNameAndDate(String information) {
        // Use regular expression to extract the name and deadline date
        return Pattern.compile("(?<name>.*)/by\\s*(?<date>.*)").matcher(information);
    }

    private static AddTaskCommand createDeadlineTaskCommand(Matcher dateChecker) throws UnknownCommandException {
        // Extract the name and date from the Matcher object
        String name = dateChecker.group("name").trim();
        String date = dateChecker.group("date").trim();
        try {
            // Create a new DeadlineTask and return an AddTaskCommand with it
            return new AddTaskCommand(new Deadline(name, TimeParser.parseToLocalDateTime(date)));
        } catch (DateTimeParseException e) {
            // If the date format is incorrect, throw an exception
            throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
        }
    }

    public static AddTaskCommand parseEventCommand(String information) throws UnknownCommandException {
        // Check if the input string is empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_EVENT_ERROR);
        }

        // Extract the task name and time interval
        Matcher intervalChecker = extractNameAndInterval(information);

        // If the task name and time interval are extracted successfully, create a new event task command
        if (intervalChecker.matches()) {
            return createEventTaskCommand(intervalChecker);
        } else {
            // Otherwise, throw an exception for invalid format
            throw new UnknownCommandException(ErrorMessages.INVALID_EVENT_FORMAT_ERROR);
        }
    }

    private static Matcher extractNameAndInterval(String information) {
        // Extract the task name and interval using the pattern "(?<name>.*)/from(?<from>.*)/to(?<to>.*)"
        return Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)").matcher(information);
    }

    private static AddTaskCommand createEventTaskCommand(Matcher intervalChecker) throws UnknownCommandException {
        // Extract the name and interval of the task from the matcher
        String name = intervalChecker.group("name").trim();
        String from = intervalChecker.group("from").trim();
        String to = intervalChecker.group("to").trim();

        try {
            // Create a new event task with the extracted information and return the corresponding AddTaskCommand
            return new AddTaskCommand(new Event(name,
                    TimeParser.parseToLocalDateTime(from), TimeParser.parseToLocalDateTime(to)));
        } catch (DateTimeParseException e) {
            // If the date and time specified in the interval are invalid, throw an InvalidInputException
            throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
        }
    }
}
