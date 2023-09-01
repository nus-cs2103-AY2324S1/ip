package duke;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.parser.CommandParser;
import duke.parser.DeadlineCommandParser;
import duke.parser.EventCommandParser;
import duke.parser.ListCommandParser;
import exceptions.IncorrectCommandFormatException;

/**
 * The Commands enum represents the different commands that can be executed by
 * the user.
 */
public enum Commands {
    /**
     * The different commands that can be done in the chatbot and their respective
     * string representation and their regex pattern when input the command.
     */

    // DEADLINE("deadline", "^(deadline)(?:( .*?) /by( .*))?$"),
    // EVENT("event", "^(event)(?:( .*?) /from( .*?) /to( .*))?$");

    // LIST("list", "^list$"),
    // MARK("mark", "^(mark) (-?\\d+)$"),
    // UNMARK("unmark", "^(unmark) (-?\\d+)$"),
    // DELETE("delete", "^(delete) (-?\\d+)$"),
    // TASK("task", "^(deadline|event|todo) .*"),
    // TODO("todo", "^(todo)(?: (.*))?$"),
    // DEADLINE("deadline", "^(deadline)(?:( .*?) /by( \\d{4}-\\d{2}-\\d{2}))?$"),
    // EVENT("event",
    // "^(?P<command>event)(?: (?P<description>.*?) /from
    // (?P<from_time>\\d{4}-\\d{2}-\\d{2}) /to
    // (?P<to_time>\\d{4}-\\d{2}-\\d{2}))?$");

    LIST("list", new ListCommandParser("list", "^list$")), 
    EVENT("event", new EventCommandParser()), 
    DEADLINE("deadline", new DeadlineCommandParser());

    public final String commandString;
    private final CommandParser commandParser;

    /**
     * The string representation of the command.
     */
    // public final String commandString;

    /**
     * The pattern that the command takes.
     */
    // public final Pattern commandParser;

    /**
     * Constructs a new command with the specified string representation and one
     * parameter.
     *
     * @param commandString the string representation of the command
     * @param pattern the pattern that the command takes
     */
    private Commands(String commandString, CommandParser commandParser) {
        this.commandString = commandString;
        this.commandParser = commandParser;
    }

    public CommandParser getParser() {
        return this.commandParser;
    }
}
// /**
// * Parses the raw input string and returns the corresponding command.
// *
// * @param rawInput the raw input string
// * @return the corresponding command, or null if the input is invalid
// */
// public static Commands parseCommand(String rawInput) throws
// UnknownCommandException,
// MissingDescriptionException, IncorrectCommandFormatException,
// InvalidTimeFormatException {
// for (Commands c : values()) {
// Matcher matcher = c.commandPattern.matcher(rawInput);
// if (matcher.find()) {
// if (c == TASK) {
// return matchTask(rawInput);
// }
// return c;
// }
// }
// throw new UnknownCommandException(rawInput);
// }

// public static Commands matchTask(String input)
// throws MissingDescriptionException, IncorrectCommandFormatException,
// InvalidTimeFormatException {
// Matcher todoMatcher = TODO.commandPattern.matcher(input);
// Matcher deadlineMatcher = DEADLINE.commandPattern.matcher(input);
// Matcher eventMatcher = EVENT.commandPattern.matcher(input);

// if (todoMatcher.matches()) {
// String description = todoMatcher.group(1);
// if (description == null) {
// throw new MissingDescriptionException("todo");
// }
// return TODO;
// }

// if (deadlineMatcher.matches()) {
// return DEADLINE;
// } else if (eventMatcher.find()) {
// return EVENT;
// } else {
// throw new MissingDescriptionException("task");
// }
// }

// }

// public static void extractCommand(String input, TaskListStorage
// tasklistStorage)
// throws UnknownCommandException {
// Commands command = Commands.parseCommand(input);
// switch (command) {
// case LIST:
// tasklistStorage.printList();
// break;
// case MARK:
// tasklistStorage.markAsDone(input);
// break;
// case UNMARK:
// tasklistStorage.markAsUndone(input);
// break;
// case DELETE:
// tasklistStorage.deleteTask(input);
// case TASK:

// break;
// default:
// throw new UnknownCommandException(input);
// }
// }

// /**
// * The following methods are used to extract the description for the task
// * commands.
// *
// * @param input the raw input string
// * @return the corresponding tasks description, or null if the input is
// invalid
// * @throws MissingDescriptionException
// */
// public static String extractTaskDescription(String input) throws
// MissingDescriptionException {
// // create a task pattern for the three tasks
// List<Commands> taskCommands = List.of(TODO, DEADLINE, EVENT);

// for (Commands taskCommand : taskCommands) {
// Matcher matcher = taskCommand.commandPattern.matcher(input);
// if (matcher.find()) {
// String match = matcher.group(2); // Store the description once a match is
// found
// if (match == null || match.trim().isEmpty()) {
// throw new MissingDescriptionException(matcher.group(1));
// }
// return match.trim();
// }
// }
// return null;
// }

// /**
// * The following methods are used to extract the different parameters for the
// * task commands.
// *
// * @param input the raw input string
// * @return the corresponding parameter, or null if the input is invalid
// * @throws IncorrectCommandFormatException
// */
// public static LocalDate extractDeadline(String input) throws
// IncorrectCommandFormatException {
// Matcher matcher = DEADLINE.commandPattern.matcher(input);
// if (matcher.find()) {
// String match = matcher.group(3);
// if (match == null || match.trim().isEmpty()) {
// throw new IncorrectCommandFormatException("/by", "deadline <description> /by
// <date>");
// }
// return LocalDate.parse(match.trim());
// }
// return null;
// }

// public static LocalDate extractEventFrom(String input) throws
// IncorrectCommandFormatException {
// Matcher matcher = EVENT.commandPattern.matcher(input);
// if (matcher.find()) {
// String match = matcher.group(3);
// if (match == null || match.trim().isEmpty()) {
// throw new IncorrectCommandFormatException("/from",
// "event <description> /from <from_date> /to <to_date>");
// }
// return LocalDate.parse(match.trim());
// }
// return null;
// }

// public static LocalDate extractEventTo(String input) throws
// IncorrectCommandFormatException {
// Matcher matcher = EVENT.commandPattern.matcher(input);
// if (matcher.find()) {
// String match = matcher.group(4);
// if (match == null || match.trim().isEmpty()) {
// throw new IncorrectCommandFormatException("/to", "event <description> /from
// <from_date> /to <to_date>");
// }
// return LocalDate.parse(match.trim());
// }
// return null;
// }