package pardiyem.parser;

import pardiyem.command.AddCommand;
import pardiyem.command.ByeCommand;
import pardiyem.command.Command;
import pardiyem.command.DeleteCommand;
import pardiyem.command.FindCommand;
import pardiyem.command.ListCommand;
import pardiyem.command.MarkCommand;
import pardiyem.command.UnmarkCommand;

public class Parser {
    private static final String SPACE = " ";
    private static final String UNRECOGNIZABLE_MSG = "Whoops, I do not recognize that command";
    private static final int INVALID_INDEX = -1;
    private static final int STARTING_INDEX = 0;
    private static final int TODO_ID = 1;
    private static final int DEADLINE_ID = 2;
    private static final int EVENT_ID = 3;

    /**
     * Parser method that takes in a string of arguments and outputs a corresponding command
     *
     * @param in string of arguments inputted by the user
     * @return Command object corresponding to inputted string
     * @throws NoSuchMethodException if the first word in the string, the command argument, cannot be recognized
     */
    public static Command parseCommand(String in) throws NoSuchMethodException {
        String command;
        int x = in.indexOf(SPACE);
        if (x == INVALID_INDEX) {
            command = in;
        } else {
            command = in.substring(STARTING_INDEX, x);
        }
        switch (command) {
            case "bye":
                return new ByeCommand(in.substring(StringSkip.THREE.getSkipBy()).trim());
            case "list":
                return new ListCommand(in.substring(StringSkip.FOUR.getSkipBy()).trim());
            case "mark":
                return new MarkCommand(in.substring(StringSkip.FOUR.getSkipBy()).trim());
            case "unmark":
                return new UnmarkCommand(in.substring(StringSkip.SIX.getSkipBy()).trim());
            case "todo":
                return new AddCommand(in.substring(StringSkip.FOUR.getSkipBy()).trim(), TODO_ID);
            case "deadline":
                return new AddCommand(in.substring(StringSkip.EIGHT.getSkipBy()).trim(), DEADLINE_ID);
            case "event":
                return new AddCommand(in.substring(StringSkip.FIVE.getSkipBy()).trim(), EVENT_ID);
            case "delete":
                return new DeleteCommand(in.substring(StringSkip.SIX.getSkipBy()).trim());
            case "find":
                return new FindCommand(in.substring(StringSkip.FOUR.getSkipBy()).trim());
            default:
                throw new NoSuchMethodException(UNRECOGNIZABLE_MSG);
        }
    }
}
