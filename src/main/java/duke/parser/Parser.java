package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        if (!input.contains(" ")) {
            if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (input.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else {
                throw new DukeException("I don't know what that means");
            }
        } else {
            String task = input.substring(0, input.indexOf(" "));
            if (task.equalsIgnoreCase("mark")) {
                return new MarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
            } else if (task.equalsIgnoreCase("unmark")) {
                return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
            } else if (task.equalsIgnoreCase("delete")) {
                return new DeleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
            } else {
                return new AddCommand(input);
            }
        }
    }
}

