package duke.parser;

import duke.exceptions.DukeUnknownCommandException;

import duke.command.Command;

public class Parser {
    public Command parseInput(String input) throws DukeUnknownCommandException {
        String[] inputs = input.split(" ", 2);

        String command = inputs[0].toLowerCase();

        if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("deadline")) {
            return Command.DEADLINE;
        } else if (command.equals("event")) {
            return Command.EVENT;
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.equals("mark")) {
            return Command.MARK;
        } else if (command.equals("unmark")) {
            return Command.UNMARK;
        } else {
            throw new DukeUnknownCommandException(command);
        }
    }
}
