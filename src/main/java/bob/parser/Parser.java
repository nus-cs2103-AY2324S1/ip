package bob.parser;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.enums.CommandType;
import bob.exception.BobCorruptFileException;
import bob.exception.BobException;
import bob.exception.BobInvalidCommandException;
import bob.exception.BobInvalidTaskNumberException;
import bob.exception.BobMissingArgumentException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Processes user input or save files to executable commands.
 */
public class Parser {


    /**
     * Returns an instance of a Command with specific user input to be executed.
     *
     * @param input Text input to be parsed
     * @return Executable command based on input
     * @throws BobException If command given has invalid format
     */
    public static Command parse(String input) throws BobException {
        String[] inputSplit = input.split(" ", 2);

        CommandType commandType = strToCommandType(inputSplit[0].toUpperCase());
        String argument = "";
        if (inputSplit.length >= 2) {
            argument = inputSplit[1];
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
            return Parser.parseMarkCommand(commandType, argument);
        case TODO:
            return Parser.parseTodoCommand(commandType, argument);
        case DEADLINE:
            return Parser.parseDeadlineCommand(commandType, argument);
        case EVENT:
            return Parser.parseEventCommand(commandType, argument);
        case DELETE:
            return Parser.parseDeleteCommand(argument);
        case FIND:
            return Parser.parseFindCommand(argument);
        case INVALID:
            throw new BobInvalidCommandException("I'm sorry! I don't understand the command :(");
        default:
            throw new BobInvalidCommandException("I'm sorry! Something went wrong when processing your command :(");
        }
    }

    private static Command parseMarkCommand(CommandType commandType, String argument)
            throws BobInvalidTaskNumberException {
        if (argument.isBlank()) {
            throw new BobInvalidTaskNumberException("Give me a task number to mark/unmark as done!");
        }
        try {
            return new MarkCommand(Integer.parseInt(argument), commandType == CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new BobInvalidTaskNumberException("The mark/unmark command "
                    + "needs to be followed up by an integer number!\n");
        }
    }

    private static Command parseTodoCommand(CommandType commandType, String argument)
            throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your todo "
                    + "should not be empty! Try:\ntodo [description]");
        }
        return new AddCommand(commandType, argument, null, null);
    }

    private static Command parseDeadlineCommand(CommandType commandType, String argument)
            throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your deadline should not be empty!"
                    + " Try:\ndeadline [description] /by [duedate]");
        }
        String[] bySplit = argument.split(" /by ", 2);
        if (bySplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect deadline command format!"
                    + " It should be:\ndeadline [description] /by [duedate]");
        }
        return new AddCommand(commandType, bySplit[0], null, bySplit[1]);
    }

    private static Command parseEventCommand(CommandType commandType, String argument)
            throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your event should not be empty! Try:\n"
                    + "event [description] /from [start] /to [end]");
        }
        String[] descSplit = argument.split(" /from ", 2);
        if (descSplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect event command format! It should be:\n"
                    + "event [description] /from [start] /to [end]");
        }
        String[] periodSplit = descSplit[1].split(" /to ", 2);
        if (periodSplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect event command format! It should be:\n"
                    + "event [description] /from [start] /to [end]");
        }
        return new AddCommand(commandType, descSplit[0], periodSplit[0], periodSplit[1]);
    }

    private static Command parseDeleteCommand(String argument) throws BobInvalidTaskNumberException {
        if (argument.isBlank()) {
            throw new BobInvalidTaskNumberException("Give me a task number to delete!");
        }
        try {
            return new DeleteCommand(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new BobInvalidTaskNumberException("The delete command needs to be followed up "
                    + "by an integer number!\n");
        }
    }

    private static Command parseFindCommand(String argument) {
        return new FindCommand(argument);
    }

    private static CommandType strToCommandType(String s) {
        try {
            return CommandType.valueOf(s);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    /**
     * Returns an instance of a Task after processing a String in stored format.
     *
     * @param inputString Text input to be parsed
     * @return An instance of Task based on input string
     * @throws BobException If input string has incorrect format
     */
    public static Task parseStoredTask(String inputString) throws BobException {
        String[] split = inputString.split("\\|");
        String commandType = split[0];

        try {
            switch (commandType) {
            case "T":
                return new Todo(split[2], Integer.parseInt(split[1]) == 1);
            case "D":
                return new Deadline(split[2], split[3], Integer.parseInt(split[1]) == 1);
            case "E":
                return new Event(split[2], split[3], split[4], Integer.parseInt(split[1]) == 1);
            default:
            }
            return null;
        } catch (Exception e) {
            throw new BobCorruptFileException("Your save file might be corrupted :(");
        }
    }
}
