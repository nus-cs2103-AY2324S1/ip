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
        String[] splitCommandAndArguments = fullCommand.split(" ", 2);
        String commandWord;
        String arguments = "";

        if (splitCommandAndArguments.length == 2) {
            arguments = splitCommandAndArguments[1];
        }
        commandWord = splitCommandAndArguments[0];

        switch (commandWord) {
        case "todo":
            return new AddTodoCommand(commandWord, arguments);
        case "event":
            return new AddEventCommand(commandWord, arguments);
        case "deadline":
            return new AddDeadlineCommand(commandWord, arguments);
        case "list":
            return new ListCommand(commandWord, arguments);
        case "delete":
            return new DeleteCommand(commandWord, arguments);
        case "bye":
            return new ExitCommand(commandWord, arguments);
        case "mark":
            return new MarkCommand(commandWord, arguments);
        case "unmark":
            return new UnmarkCommand(commandWord, arguments);
        case "find":
            return new FindCommand(commandWord, arguments);
        case "update":
            return new UpdateCommand(commandWord, arguments);
        default:
            return new HelpCommand(commandWord, arguments);
        }
    }
}
