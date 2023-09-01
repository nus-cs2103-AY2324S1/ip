package duke;

import commands.Commands;
import duke.exceptions.UnknownCommandException;
import duke.parser.CommandParser;

public class Parser {
    public static void dispatch(String input) throws UnknownCommandException {
        String commandName = input.trim().split(" ")[0];

        try {
            Commands commandType = Commands.valueOf(commandName);
            CommandParser parser = commandType.getParser();
            parser.parse(input);
            parser.execute(input);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Unknown command: " + commandName);
        }
    }
}