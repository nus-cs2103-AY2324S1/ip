package duke.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

// Solution below inspired by https://github.com/Yufannnn/ip
/**
 * Represents a parser that processes command inputs.
 */
public class CommandParser {
    /**
     * Parses the input string to return a corresponding Command.
     *
     * @param input The string input from the user.
     * @return A Command corresponding to the input string.
     * @throws Exception If there's an error in parsing the input.
     */
    public static Command parse(String input) throws Exception {
        Matcher instructionExtractor = extractInstructionAndInformation(input);
        String instructionTag = instructionExtractor.group("instructionTag").trim();
        String information = instructionExtractor.group("information").trim();

        CommandType instruction = matchFlag(instructionTag);
        return createCommand(instruction, information);
    }

    /**
     * Extracts the instruction and its corresponding information from the input.
     *
     * @param input The string input from the user.
     * @return A Matcher containing extracted instruction and information.
     * @throws UnknownCommandException If the instruction cannot be recognized.
     */
    private static Matcher extractInstructionAndInformation(String input) throws UnknownCommandException {
        Matcher instructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.trim());

        if (!instructionExtractor.matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_ERROR);
        }

        return instructionExtractor;
    }

    /**
     * Converts the type flag to its corresponding CommandType.
     *
     * @param typeFlag The type flag string.
     * @return The corresponding CommandType.
     * @throws UnknownCommandException If the type flag cannot be recognized.
     */
    private static CommandType matchFlag(String typeFlag) throws UnknownCommandException {
        try {
            return CommandType.valueOf(typeFlag.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }

    private static Command createCommand(CommandType commandType, String information)
            throws UnknownCommandException {
        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
        case DELETE:
            return ParserHelper.parseCommandByType(commandType, information);
        case TODO:
            return ParserHelper.parseTodoCommand(information);
        case DEADLINE:
            return ParserHelper.parseDeadlineCommand(information);
        case EVENT:
            return ParserHelper.parseEventCommand(information);
        case FIND:
            return ParserHelper.parseFindCommand(information);
        case HELP:
            return ParserHelper.parseHelpCommand(information);
        default:
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }
}
