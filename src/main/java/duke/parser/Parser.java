package duke.parser;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TaskCommand;

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
                default:
                    throw new DukeNotTaskException("");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeNotTaskException(e.getMessage());
        }
    }
}
