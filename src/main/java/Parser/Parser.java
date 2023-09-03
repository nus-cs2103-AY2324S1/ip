package Parser;

import Command.*;
import enums.CommandWord;

public class Parser {
    public static Command parse(String rawCommand) {
        switch (CommandWord.commandWordToValueMap(Command.getArgs(rawCommand)[0])) {
            case BYE:
                return new ByeCommand(rawCommand);
            case LIST:
                return new ListCommand(rawCommand);
            case MARK:
                return new MarkCommand(rawCommand);
            case UNMARK:
                return new UnmarkCommand(rawCommand);
            case DELETE:
                return new DeleteCommand(rawCommand);
            case TODO:
                return new TodoCommand(rawCommand);
            case DEADLINE:
                return new DeadlineCommand(rawCommand);
            case EVENT:
                return new EventCommand(rawCommand);
            default:
                return new NullCommand(rawCommand);
        }
    }
}
