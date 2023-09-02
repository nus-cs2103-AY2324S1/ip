package duke;

import duke.commands.Command;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.parser.CommandParser;
import duke.parser.DeadlineCommandParser;
import duke.parser.EventCommandParser;
import duke.parser.IndexCommandParser;
import duke.parser.SimpleCommandParser;
import duke.parser.TodoCommandParser;
import duke.parser.WordCommandParser;

public class Parser {

    public enum Commands {

        // @formatter:off
        LIST("list", new SimpleCommandParser("list")), 
        MARK("mark", new IndexCommandParser("mark")),
        UNMARK("unmark", new IndexCommandParser("unmark")),
        DELETE("delete", new IndexCommandParser("delete")),
        FIND("find", new WordCommandParser("find")),
        TODO("todo", new TodoCommandParser()),
        DEADLINE("deadline", new DeadlineCommandParser()),
        EVENT("event", new EventCommandParser()); 
        // @formatter:on

        public final String commandString;
        private final CommandParser commandParser;

        /**
         * Returns the command with the specified string representation.
         * Idea was taken from https://www.baeldung.com/java-enum-values#locating-values
         *
         * @param commandString the string representation of the command
         * @return the command with the specified string representation
         */
        public static Commands valueOfLabel(String commandString) {
            for (Commands commands : values()) {
                if (commands.commandString.equals(commandString)) {
                    return commands;
                }
            }
            return null;
        }

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

    public Command dispatch(String input)
            throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException,
            InvalidIndexException, InvalidTimeFormatException {
        String commandName = input.trim().split(" ")[0];

        try {
            Commands commandType = Commands.valueOfLabel(commandName);
            CommandParser parser = commandType.getParser();
            return parser.parse(input);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UnknownCommandException("Unknown command: " + commandName);
        }
    }
}