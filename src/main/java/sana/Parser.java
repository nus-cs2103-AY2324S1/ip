package sana;

/**
 * Represents a parser to parse user input into commands.
 */
public class Parser {

    /**
     * Parses user input into a command type and its description.
     *
     * @param fullCommand command that user types in.
     * @return returns a command according to the command type.
     */
    public static Command parse(String fullCommand) {
        String[] strArr = fullCommand.split(" ", 2);
        String cmdWord;
        String arguments = "";
        if (strArr.length == 2) {
            arguments = strArr[1];
        }
        cmdWord = strArr[0];

        switch (cmdWord) {
        case "todo": case "event": case "deadline":
            return new AddCommand(cmdWord, arguments);
        case "list":
            return new ListCommand(cmdWord, arguments);
        case "delete":
            return new DeleteCommand(cmdWord, arguments);
        case "bye":
            return new ExitCommand(cmdWord, arguments);
        case "mark":
            return new MarkCommand(cmdWord, arguments);
        case "unmark":
            return new UnmarkCommand(cmdWord, arguments);
        case "find":
            return new FindCommand(cmdWord, arguments);
        default:
            return new HelpCommand(cmdWord, arguments);
        }
    }
}
