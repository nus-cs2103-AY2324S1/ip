package duke.parser;

import duke.exceptions.DukeUnknownCommandException;

import duke.command.Command;

/**
 * Represents a parser to parse
 * the users' input.
 *
 * @author Andrew Daniel Janong
 */
public class Parser {
    /**
     * Parses the input of the user and returns the Command.
     *
     * @param input Input of the user.
     * @return Command of the input.
     * @throws DukeUnknownCommandException Error when the command input is unknwon.
     */
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
        } else if (command.equals("find")) {
            return Command.FIND;
        } else if (command.equals("mark")) {
            return Command.MARK;
        } else if (command.equals("unmark")) {
            return Command.UNMARK;
        } else {
            throw new DukeUnknownCommandException(command);
        }
    }
}
