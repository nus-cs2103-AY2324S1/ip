package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parses the inputs given by the user
 *
 * @author Armando Jovan Kusuma
 */
public class Parser {

    /**
     * Parses the user input into a command.
     *
     * @param input The user input to be parsed.
     * @return the Command that represents the user input.
     * @throws DukeException When the user input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        try {
            if(!input.contains(" ")) {
                switch(input.toLowerCase()) {
                case "list":
                case "l":
                    return new ListCommand();
                case "bye":
                    return new ExitCommand();
                case "help":
                case "h":
                    return new HelpCommand();
                case "todo":
                case "deadline":
                case "event":
                case "t":
                case "d":
                case "e":
                    return new AddCommand(input);
                default:
                    return new InvalidCommand(new DukeException(" Oops! I'm sorry, I don't know what that means..."));
                }
            } else {
                String task = input.substring(0, input.indexOf(" ")).toLowerCase();
                switch (task) {
                case "mark":
                case "m":
                    return new MarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case "unmark":
                case "um":
                    return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case "delete":
                case "del":
                    return new DeleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case "find":
                case "f":
                    return new FindCommand(input.split(" ", 2)[1].trim());
                default:
                    return new AddCommand(input);
                }
            }
        } catch (Exception e) {
            return new InvalidCommand(new DukeException(" Oops! I'm sorry, I don't know what that means..."));
        }
    }
}

