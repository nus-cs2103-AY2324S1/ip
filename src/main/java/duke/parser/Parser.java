package duke.parser;

import duke.Duke;
import duke.command.*;
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
            if (!input.contains(" ")) {
                if (input.equalsIgnoreCase("list")) {
                    return new ListCommand();
                } else if (input.equalsIgnoreCase("bye")) {
                    return new ExitCommand();
                } else if(input.equalsIgnoreCase("todo") ||
                        input.equalsIgnoreCase("deadline") ||
                        input.equalsIgnoreCase("event")) {
                    return new AddCommand(input);
                } else {
                    return new InvalidCommand(new DukeException(" Oops! I'm sorry, I don't know what that means"));
                }
            } else {
                String task = input.substring(0, input.indexOf(" "));
                if (task.equalsIgnoreCase("mark")) {
                    return new MarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else if (task.equalsIgnoreCase("unmark")) {
                    return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else if (task.equalsIgnoreCase("delete")) {
                    return new DeleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else if (task.equalsIgnoreCase("find")) {
                    return new FindCommand(input.split(" ", 2)[1].trim());
                } else {
                    return new AddCommand(input);
                }
            }
        } catch (Exception e) {
            return new InvalidCommand(new DukeException(" Oops! I'm sorry, I don't know what that means"));
        }
    }
}

