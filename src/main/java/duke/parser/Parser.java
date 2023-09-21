package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.alias.AliasMap;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.alias.AddAliasCommand;
import duke.command.alias.DeleteAliasCommand;
import duke.command.alias.ListAliasCommand;
import duke.command.task.DeadlineTaskCommand;
import duke.command.task.DeleteTaskCommand;
import duke.command.task.EventTaskCommand;
import duke.command.task.FindTaskCommand;
import duke.command.task.ListTaskCommand;
import duke.command.task.MarkTaskCommand;
import duke.command.task.ToDoTaskCommand;
import duke.command.task.UnmarkTaskCommand;
import duke.exception.DukeException;

/**
 * The `Parser` class is responsible for parsing user input commands into executable commands.
 */
public class Parser {

    /**
     * The date and time format used for parsing date-time inputs.
     */
    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private AliasMap aliasMap;
    private boolean hasAliasMap;

    /**
     * Constructs a Parser object with an alias map.
     *
     * @param aliasMap The alias map to use for command parsing.
     */
    public Parser(AliasMap aliasMap) {
        this();
        if (aliasMap != null) {
            this.aliasMap = aliasMap;
            this.hasAliasMap = true;
        }
    }

    /**
     * Constructs a Parser object without an alias map.
     */
    public Parser() {
        this.aliasMap = null;
        this.hasAliasMap = false;
    }

    /**
     * Parses an alias if available, otherwise returns the original input.
     *
     * @param input The input to parse as an alias.
     * @return The parsed alias or the original input if no alias is found.
     */
    protected String parseAlias(String input) {
        if (!hasAliasMap) {
            return input;
        }
        String parsedFull = this.aliasMap.getFullCommand(input);
        if (parsedFull == null) {
            return input;
        }
        return parsedFull;
    }

    /**
     * Parses a user input command and returns the corresponding `Command` object.
     *
     * @param command The user input command to be parsed.
     * @return A `Command` object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or contains invalid arguments.
     */
    public Command parse(String command) throws DukeException {
        String[] commandArr = command.split(" ", 2);
        String keywordInput = commandArr[0];
        String description;

        String keyword = parseAlias(keywordInput);
        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListTaskCommand();
        case "find":
            if (commandArr.length < 2) {
                throw new DukeException("\"OOPS!!! Please include the keyword you would like to search.");
            }
            String searchWord = commandArr[1].trim();
            return new FindTaskCommand(searchWord);
        case "mark":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to mark.");
            }
            try {
                int markNumber = Integer.parseInt(commandArr[1].trim());
                return new MarkTaskCommand(markNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "unmark":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to unmark.");
            }
            try {
                int unmarkNumber = Integer.parseInt(commandArr[1].trim());
                return new UnmarkTaskCommand(unmarkNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "delete":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to delete.");
            }
            try {
                int deleteNumber = Integer.parseInt(commandArr[1].trim());
                return new DeleteTaskCommand(deleteNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "todo":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            description = commandArr[1];
            return new ToDoTaskCommand(description);
        case "deadline":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            description = commandArr[1];
            String deadlineName = "";
            String deadlineBy = "";
            String[] deadlineDescription = description.split("/");
            for (String str : deadlineDescription) {
                if (str.startsWith("by")) {
                    deadlineBy = str.split(" ", 2)[1].trim();
                } else {
                    deadlineName = str.trim();
                }
            }
            if (deadlineName.isEmpty()) {
                throw new DukeException("OOPS!! Please include the name of the deadline.");
            }
            if (deadlineBy.isEmpty()) {
                throw new DukeException("OOPS!! Please include when the deadline is by.");
            }
            try {
                LocalDateTime byParsed = LocalDateTime.parse(deadlineBy, DATETIME_INPUT_FORMAT);
                return new DeadlineTaskCommand(deadlineName, byParsed);
            } catch (DateTimeParseException e) {
                throw new DukeException(DukeException.WRONG_DATETIME_MESSAGE);
            }
        case "event":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            description = commandArr[1];
            String eventName = "";
            String eventFrom = "";
            String eventTo = "";
            String[] eventDescription = description.split("/");
            for (String str : eventDescription) {
                if (str.startsWith("from")) {
                    eventFrom = str.split(" ", 2)[1].trim();
                } else if (str.startsWith("to")) {
                    eventTo = str.split(" ", 2)[1].trim();
                } else {
                    eventName = str.trim();
                }
            }
            if (eventName.isEmpty()) {
                throw new DukeException("OOPS!! Please include the name of the event.");
            }
            if (eventFrom.isEmpty()) {
                throw new DukeException("OOPS!! Please include when the event is from.");
            }
            if (eventTo.isEmpty()) {
                throw new DukeException("OOPS!! Please include when the event is till.");
            }
            try {
                LocalDateTime fromParsed = LocalDateTime.parse(eventFrom, DATETIME_INPUT_FORMAT);
                LocalDateTime toParsed = LocalDateTime.parse(eventTo, DATETIME_INPUT_FORMAT);
                return new EventTaskCommand(eventName, fromParsed, toParsed);
            } catch (DateTimeParseException e) {
                throw new DukeException(DukeException.WRONG_DATETIME_MESSAGE);
            }
        case "alias_add":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please provide me with the alias and full command.");
            }
            description = commandArr[1];
            String alias = "";
            String fullCommand = "";
            String[] aliasDescription = description.split("=");
            if (aliasDescription.length != 2) {
                throw new DukeException("OOPS!! Please use this format: ALIAS=FULLCOMMAND");
            }
            alias = aliasDescription[0].trim();
            fullCommand = aliasDescription[1].trim();
            return new AddAliasCommand(alias, fullCommand);
        case "alias_delete":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please provide me with the alias to delete.");
            }
            String aliasToDelete = commandArr[1].trim();
            return new DeleteAliasCommand(aliasToDelete);

        case "alias_list":
            return new ListAliasCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
