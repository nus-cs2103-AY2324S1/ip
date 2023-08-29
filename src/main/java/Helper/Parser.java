package Helper;

import Command.Command;

import java.time.LocalDate;

import Exception.*;
import Command.*;

public class Parser {
    public static Command parse(String fullCommand) throws WrongUseOfCommandException, MissingIndexException, InvalidCommandException, MissingTaskException {
        fullCommand = fullCommand.trim();

        if (fullCommand.startsWith("bye") || fullCommand.startsWith("list")) {
            if (fullCommand.equals(ExitCommand.COMMAND_WORD)) {
                return new ExitCommand(-1);
            }
            if (fullCommand.equals(ListCommand.COMMAND_WORD)) {
                return new ListCommand(-1);
            }
            throw new WrongUseOfCommandException();
        }

        if (fullCommand.startsWith("mark") || fullCommand.startsWith("unmark") ||
                fullCommand.startsWith("delete") || fullCommand.startsWith("due")) {
            try {
                String[] res = fullCommand.split(" ", 2);
                String taskType = res[0].strip();

                if (taskType.equals(DueCommand.COMMAND_WORD)) {
                    return new DueCommand(-1, LocalDate.parse(res[1].strip()));
                }

                int index = Integer.parseInt(res[1].strip());

                switch (taskType) {
                    case MarkCommand.COMMAND_WORD:
                        return new MarkCommand(index);
                    case UnmarkCommand.COMMAND_WORD:
                        return new UnmarkCommand(index);
                    case DeleteCommand.COMMAND_WORD:
                        return new DeleteCommand(index);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingIndexException(fullCommand);
            }
        }

        if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event")) {
            if (fullCommand.equals("todo") || fullCommand.equals("deadline") || fullCommand.equals("event")) {
                throw new MissingTaskException(fullCommand);
            }

            return new AddCommand(-1, fullCommand);
        }

        throw new InvalidCommandException(fullCommand);
    }
}
