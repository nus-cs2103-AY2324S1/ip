package duke.parser;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.ui.Ui;


/**
 * Represents a parser for handling user inputs and generating corresponding commands.
 */
public class Parser {

    /**
     * Handles the user input and returns the corresponding command.
     *
     * @param input The user input to be parsed.
     * @return A Command object based on the parsed input.
     * @throws DukeException If there is an issue parsing the input or creating the command.
     */
    public static Command handleInput(String input) throws DukeException {
        Ui ui = new Ui();
        String[] arr = input.split(" ", 2);
        if (arr.length == 1) {
            arr = new String[] { arr[0], "" };
        }
        String type = arr[0];
        if ("bye".equals(type)) {
            return new ExitCommand();

        } else if (type.equals("mark") || type.equals("unmark")) {
            return new MarkCommand(type, arr[1]);

        } else if (type.equals("deadline") || type.equals("event") || type.equals("todo")) {
            return new AddCommand(type, arr[1]);

        } else if ("list".equals(input.strip())) {
            return new ListCommand();

        } else if (type.equals("delete")) {
            return new DeleteCommand(arr[1]);

        } else if (type.equals("find")) {
            return new FindCommand(arr[1]);

        } else if (type.equals("help")) {
            return new HelpCommand();

        } else {
            throw new DukeException(ui.showInvalidInput());
        }
    }
}
