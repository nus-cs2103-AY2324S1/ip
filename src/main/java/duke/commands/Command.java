package duke.commands;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public static Command parse(String s) throws DukeException {
        String[] tokens = s.split(" ", 2);
        String args = tokens.length > 1 ? tokens[1] : "";

        try {
            Verb verb = Verb.valueOf(tokens[0].toUpperCase());
            switch (verb) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case TODO:
                    return new TodoCommand(args);
                case DEADLINE:
                    return new DeadlineCommand(args);
                case EVENT:
                    return new EventCommand(args);
                case MARK:
                    return new MarkCommand(args);
                case UNMARK:
                    return new UnmarkCommand(args);
                case DELETE:
                    return new DeleteCommand(args);
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException("I'm sorry, but I don't know what that means...");
        }
        return null;
    }

    public boolean isExit() {
        return false;
    }

    public abstract CommandResult run(TaskList tasks) throws CommandException;

    public enum Verb {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
