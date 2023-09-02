package duke.parser;

import duke.command.*;
import duke.exception.DukeNotTaskException;

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
        FIND,
    }

    public static Command parse(String input) throws Exception {
        try {
            CommandWord commandWord = CommandWord.valueOf(input.split(" ")[0].toUpperCase());

            String[] splitTask = input.split(" ", 2);
            switch (commandWord) {
                case BYE:
                    return new ByeCommand();
                case DELETE:
                    if (splitTask.length == 2) {
                        return new DeleteCommand(splitTask);
                    }
                case LIST:
                    return new ListCommand();
                case MARK:
                    if (splitTask.length == 2) {
                        return new MarkCommand(splitTask);
                    }
                case UNMARK:
                    if (splitTask.length == 2) {
                        return new UnmarkCommand(splitTask);
                    }
                case TODO:
                case EVENT:
                case DEADLINE:
                    if (splitTask.length == 2) {
                        return new TaskCommand(splitTask);
                    }
                case FIND:
                    if (splitTask.length == 2) {
                        return new FindCommand(splitTask);
                    }
                default:
                    throw new DukeNotTaskException("");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeNotTaskException(e.getMessage());
        }
    }
}
