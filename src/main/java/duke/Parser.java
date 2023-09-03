package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommandException;
import command.ListCommand;
import command.MarkCommand;
import command.OnCommand;
import command.UnmarkCommand;

/**
 * A class to handle parsing of inputs.
 */
public class Parser {
    /**
     * Delimiter between words.
     */
    private static final String DELIMITER_COMMAND = " ";
    /**
     * Delimiter between dates in user input.
     */
    private static final String DELIMITER_DATE = " /";
    /**
     * Delimiter between sections in file input.
     */
    private static final String DELIMITER_INPUT = " / ";

    /**
     * Returns parsed input from the user.
     *
     * @param input Input from user.
     * @throws InvalidCommandException If the input is not of the recognised form.
     */
    public static Command parseUserInput(String input) throws InvalidCommandException {
        ArrayList<String> parsedInput = new ArrayList<>();

        String[] splitInputByDateDelimiter = input.split(DELIMITER_DATE);

        for (int i = 1; i < splitInputByDateDelimiter.length; i++) {
            String[] split = splitInputByDateDelimiter[i].split(DELIMITER_COMMAND);
            if (split.length <= 1) {
                throw new InvalidCommandException("Invalid input for date");
            }
            splitInputByDateDelimiter[i] = String.join(" ", Arrays.copyOfRange(split, 1, split.length));
        }

        String commandString = splitInputByDateDelimiter[0];
        String[] splitCommandStringByCommandDelimiter = commandString.split(DELIMITER_COMMAND);
        String command = splitCommandStringByCommandDelimiter[0];

        parsedInput.add(command);

        if (splitCommandStringByCommandDelimiter.length > 1) {
            String commandInput = String.join(" ", Arrays.copyOfRange(splitCommandStringByCommandDelimiter,
                    1, splitCommandStringByCommandDelimiter.length));
            parsedInput.add(commandInput);
        }

        Collections.addAll(parsedInput, Arrays.copyOfRange(splitInputByDateDelimiter, 1,
                splitInputByDateDelimiter.length));

        return parsedInputToCommand(parsedInput);
    }

    /**
     * Returns parsed input from the file.
     *
     * @param input Input from file.
     * @throws InvalidCommandException If the input is not of the recognised form.
     */
    public static ArrayList<String> parseFileInput(String input) throws InvalidCommandException {
        ArrayList<String> parsedInput = new ArrayList<>();
        String[] split = input.split(DELIMITER_INPUT);
        Collections.addAll(parsedInput, split);
        return parsedInput;
    }

    private static Command parsedInputToCommand(ArrayList<String> parsedInput) throws InvalidCommandException {
        if (parsedInput.size() < 1) {
            throw new InvalidCommandException("Command cannot be empty");
        }

        Command command;

        switch(parsedInput.get(0)) {
        case MarkCommand.COMMAND_MARK:
            command = new MarkCommand(parsedInput);
            break;
        case UnmarkCommand.COMMAND_UNMARK:
            command = new UnmarkCommand(parsedInput);
            break;
        case ListCommand.COMMAND_LIST:
            command = new ListCommand(parsedInput);
            break;
        case ExitCommand.COMMAND_EXIT:
            command = new ExitCommand(parsedInput);
            break;
        case DeleteCommand.COMMAND_DELETE:
            command = new DeleteCommand(parsedInput);
            break;
        case OnCommand.COMMAND_ON:
            command = new OnCommand(parsedInput);
            break;
        case FindCommand.COMMAND_FIND:
            command = new FindCommand(parsedInput);
            break;
        case AddCommand.COMMAND_ADD_TODO:
        case AddCommand.COMMAND_ADD_DEADLINE:
        case AddCommand.COMMAND_ADD_EVENT:
            command = new AddCommand(parsedInput);
            break;
        default:
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }
}
