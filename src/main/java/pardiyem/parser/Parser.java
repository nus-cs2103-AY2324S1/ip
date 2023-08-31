package pardiyem.parser;

import pardiyem.command.AddCommand;
import pardiyem.command.ByeCommand;
import pardiyem.command.Command;
import pardiyem.command.DeleteCommand;
import pardiyem.command.ListCommand;
import pardiyem.command.MarkCommand;
import pardiyem.command.UnmarkCommand;

public class Parser {
    private static final String SPACE = " ";

    public static Command parseCommand(String in) throws NoSuchMethodException {
        int x = in.indexOf(SPACE);
        String command;
        if (x == -1) {
            command = in;
        } else {
            command =  in.substring(0, x);
        }
        switch (command) {
            case "bye":
                return new ByeCommand(in.substring(3).trim());
            case "list":
                return new ListCommand(in.substring(4).trim());
            case "mark":
                return new MarkCommand(in.substring(4).trim());
            case "unmark":
                return new UnmarkCommand(in.substring(6).trim());
            case "todo":
                return new AddCommand(in.substring(4).trim(), 1);
            case "deadline":
                return new AddCommand(in.substring(8).trim(), 2);
            case "event":
                return new AddCommand(in.substring(5).trim(), 3);
            case "delete":
                return new DeleteCommand(in.substring(6).trim());
            default:
                throw new NoSuchMethodException("Whoops, I do not recognize that command");
        }
    }
}
