package duke.command;

import duke.exception.DukeException;

/**
 * Parses user input and returns the corresponding command object.
 */
public class Parser {

    /**
     * An enumeration of all possible command types.
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, FIND, BYE
    }

    /**
     * Parses the given user input and returns the corresponding command object.
     *
     * @param fullCommand The user input to be parsed.
     * @return The corresponding command object.
     * @throws DukeException If an error occurs while parsing the input.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] commandParts = fullCommand.split(" ", 2);
        String commandTypeStr = commandParts[0].toLowerCase();

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        switch (commandType) {
        case TODO:
            return new AddTodoCommand(commandParts[1]);
        case DEADLINE:
            int byIndex = commandParts[1].indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("The format of a deadline should be: deadline DESCRIPTION /by DATE");
            }
            String description = commandParts[1].substring(0, byIndex).trim();
            String by = commandParts[1].substring(byIndex + 3).trim();
            return new AddDeadlineCommand(description, by);
        case EVENT:
            int fromIndex = commandParts[1].indexOf("/from");
            if (fromIndex == -1) {
                throw new DukeException("The format of an event should be: event DESCRIPTION /from DATE /to DATE");
            }
            description = commandParts[1].substring(0, fromIndex).trim();
            String fromTo = commandParts[1].substring(fromIndex + 6).trim();
            if (fromTo.isEmpty()) {
                throw new DukeException("The format of an event should be: event DESCRIPTION /from DATE /to DATE");
            }
            String[] fromToParts = fromTo.split("/to");
            String from = fromToParts[0].trim();
            String to = fromToParts[1].trim();
            return new AddEventCommand(description, from, to);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkAsDoneCommand(Integer.parseInt(commandParts[1]));
        case UNMARK:
            return new MarkAsUndoneCommand(Integer.parseInt(commandParts[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(commandParts[1]));
        case FIND:
            return new FindCommand(commandParts[1]);
        case BYE:
            return new ExitCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
