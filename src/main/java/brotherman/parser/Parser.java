package brotherman.parser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import brotherman.commands.AddCommand;
import brotherman.commands.Command;
import brotherman.commands.DeleteCommand;
import brotherman.commands.ExitCommand;
import brotherman.commands.FindCommand;
import brotherman.commands.HelpCommand;
import brotherman.commands.ListCommand;
import brotherman.commands.MarkDoneCommand;
import brotherman.commands.UnmarkCommand;
import brotherman.exceptions.BrothermanException;
import brotherman.tasks.Deadline;
import brotherman.tasks.Event;
import brotherman.tasks.Todo;


/**
 * Represents a parser to parse user input
 */
public class Parser {
    private enum CommandType {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, HELP, BYE, INVALID
    }

    /**
     * Parses the user input and returns the corresponding command
     * @param userCommand User input
     * @return Command corresponding to the user input
     * @throws BrothermanException If the user input is invalid
     */
    public static Command parse(String userCommand) throws BrothermanException {
        Parser.isValidCommands(userCommand);
        CommandType commandType = parseType(userCommand);
        String description = parseDescription(userCommand);

        return parseToCommand(commandType, description);
    }

    /**
     * Parses the user input and returns the corresponding command
     * @param command Type of command
     * @param description Description of the command
     * @return Command corresponding to the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command parseToCommand(CommandType command, String description) throws BrothermanException {
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(description);
        case MARK:
            return handleMarkDone(description);
        case UNMARK:
            return handleUnmark(description);
        case DELETE:
            return handleDelete(description);
        case TODO:
            return handleTodo(description);
        case DEADLINE:
            return handleDeadline(description);
        case EVENT:
            return handleEvent(description);
        case HELP:
            return new HelpCommand();
        default:
            return handleInvalidCommand();
        }
    }

    /**
     * Parses the user input as a command type
     * @param input User input
     * @return Command type parsed from the user input
     */
    private static CommandType parseType(String input) {
        String userCommand = input.split(" ")[0];
        try {
            return CommandType.valueOf(userCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }
    /**
     * Parses the user input as a description
     * @param input User input
     * @return Description parsed from the user input
     */
    private static String parseDescription(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.split(" ", 2)[1].trim();
    }

    /**
     * Handles the user input as a mark command
     * @param description Description of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleMarkDone(String description) throws BrothermanException {
        int task = Integer.parseInt(description) - 1;
        return new MarkDoneCommand(task);
    }

    /**
     * Handles the user input as an unmark command
     * @param description Description of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleUnmark(String description) throws BrothermanException {
        int task = Integer.parseInt(description) - 1;
        return new UnmarkCommand(task);
    }

    /**
     * Handles the user input as a delete command
     * @param number NUmber of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleDelete(String number) throws BrothermanException {
        int task = Integer.parseInt(number) - 1;
        return new DeleteCommand(task);
    }

    /**
     * Handles the user input as a todo command
     * @param description Description of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleTodo(String description) throws BrothermanException {
        return new AddCommand(parseTodo(description));
    }

    /**
     * Handles the user input as a deadline command
     * @param description Description of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleDeadline(String description) throws BrothermanException {
        String[] deadlineArgs = description.split(" /by ");
        return new AddCommand(parseDeadline(deadlineArgs[0], deadlineArgs[1]));
    }

    /**
     * Handles the user input as an event command
     * @param description Description of the command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleEvent(String description) throws BrothermanException {
        String desc = description.split("event|/from")[1].trim();
        String[] parts = description.split("\\s*/from\\s*|\\s*/to\\s*");
        String startTime = parts[1];
        String endTime = parts[2];

        return new AddCommand(parseEvent(desc, startTime, endTime));
    }


    /**
     * Handles the user input as an invalid command
     * @return Command type parsed from the user input
     * @throws BrothermanException If the user input is invalid
     */
    private static Command handleInvalidCommand() throws BrothermanException {
        throw new BrothermanException("brotherman wtf does that even mean!");
    }



    /**
     * Checks if the user input is valid
     * @param command User input
     * @return True if the user input is valid
     * @throws BrothermanException If the user input is invalid
     */
    public static boolean isValidCommands(String command) throws BrothermanException {
        if (command.split(" ").length == 0) {
            throw new BrothermanException("Error! There is no command!");
        }
        if (command.split(" ").length == 1) {
            return true;
        }
        if (command.equals("find") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! There must be a keyword to search for!");
        }
        if (command.split(" ")[0].equals("mark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be marked!");
        }
        if (command.split(" ")[0].equals("unmark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be unmarked!");
        }
        if (command.split(" ")[0].equals("todo") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! There must be a description!!!");
        }
        if (command.split(" ")[0].equals("deadline") && command.split(" ")[1].equals("/by")) {
            throw new BrothermanException("Error! There has to be a description!!");
        }
        if (command.split(" ")[0].equals("deadline") && !command.contains("/by")) {
            throw new BrothermanException("Error! There has to be a due date/time!");
        }
        if (command.split(" ")[0].equals("event") && !command.contains("/from")) {
            throw new BrothermanException("Error! There has to be a start date/time!");
        }
        if (command.split(" ")[0].equals("event") && !command.contains("/to")) {
            throw new BrothermanException("Error! There has to be a end date/time!");
        }
        if (command.split(" ")[0].equals("event") && command.split(" ")[1].equals("/from")) {
            throw new BrothermanException("Error! There has to be a description");
        }
        return true;
    }


    /**
     * Parses the user input as a todo
     * @param description Description of the todo
     * @return Todo parsed from the user input
     */
    public static Todo parseTodo(String description) {
        return new Todo(description);
    }


    /**
     * Parses the user input as a deadline
     * @param description Description of the deadline
     * @param time Due date/time of the deadline
     * @return Deadline parsed from the user input
     */
    public static Deadline parseDeadline(String description, String time) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy][d MMM yyyy HHmm]"
        );

        LocalDateTime dateTimeObj = null;
        try {
            dateTimeObj = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            // If datetime parsing fails, try parsing as date-only
            try {
                LocalDate date = LocalDate.parse(time, formatter);
                dateTimeObj = date.atStartOfDay();
            } catch (DateTimeParseException ex) {
                System.out.println("Error parsing datetime: " + ex.getMessage());
            }
        }

        return new Deadline(description, dateTimeObj);
    }


    /**
     * Parses the user input as an event
     * @param description Description of the event
     * @param startTime Start date/time of the event
     * @param endTime End date/time of the event
     * @return Event parsed from the user input
     */
    public static Event parseEvent(String description, String startTime, String endTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy][d MMM yyyy HHmm]"
        );

        LocalDateTime startDateTimeObj = tryParseDateTime(startTime, formatter);
        LocalDateTime endDateTimeObj = tryParseDateTime(endTime, formatter);

        if (startDateTimeObj == null || endDateTimeObj == null) {
            System.out.println("Error parsing datetime");
            return null;
        }

        return new Event(description, startDateTimeObj, endDateTimeObj);
    }

    private static LocalDateTime tryParseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            return tryParseDate(dateTimeStr, formatter);
        }
    }

    private static LocalDateTime tryParseDate(String dateStr, DateTimeFormatter formatter) {
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException ex) {
            System.out.println("Error parsing date: " + ex.getMessage());
            return null;
        }
    }
}
