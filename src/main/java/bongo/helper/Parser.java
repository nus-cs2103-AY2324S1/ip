package bongo.helper;

import bongo.command.AddCommand;
import bongo.command.Command;
import bongo.command.DeleteCommand;
import bongo.command.ExitCommand;
import bongo.command.FindCommand;
import bongo.command.ListCommand;
import bongo.command.MarkCommand;
import bongo.command.UnmarkCommand;

/**
 * A class for a Parser.
 */
public class Parser {

    /**
     * Parses command that user inputs into the bot.
     * Returns the Command that matches the user's input.
     * Throws a BongoException if user enters unknown command.
     *
     * @param command Command from user input.
     * @return A Command which corresponds to user's input.
     * @throws BongoException If user gives an unknown command.
     */
    public static Command parse(String command) throws BongoException {
        String[] input = command.split(" ");
        switch (input[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new BongoException();
        }
    }
}
