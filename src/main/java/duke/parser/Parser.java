package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.Command;
import duke.command.MarkCommand;
import duke.command.ListCommand;
import duke.ui.Ui;
import duke.DukeException;

public class Parser {
    public static Command handleInput(String input) throws DukeException{
        Ui ui = new Ui();
        String[] arr = input.split(" ", 2);
        if (arr.length == 1) {
            arr = new String[] {arr[0], ""};

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

        } else {
            throw new DukeException("Oops!! That does not seem to be a valid command!");
        }
    }
}
