package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    enum CommandWord {
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
    }

    public static Command parse(String input) throws DukeException {
        try {
            CommandWord commandWord = CommandWord.valueOf(input.split(" ")[0].trim().toUpperCase());
            switch (commandWord) {
                case BYE:
                    return new ByeCommand();
                case DELETE:
                    return new DeleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case LIST:
                    return new ListCommand();
                case MARK:
                    return new MarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case UNMARK:
                    return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                case TODO:
                    try {
                        return new ToDoCommand(input.split(" ")[1].trim());
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                case DEADLINE:
                    try {
                        String[] split = input.split(" ", 2)[1].trim().split(" /by ");
                        return new DeadlineCommand(split[0], split[1]);
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of the deadline is of wrong format.");
                    }
                case EVENT:
                    try {
                        String[] split = input.split(" ", 2)[1].trim().split(" /from | /to ");
                        return new EventCommand(split[0], split[1], split[2]);
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of the event is of wrong format.");
                    }
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
