package duke.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

/**
 * Class responsible for parsing user commands.
 */
public class CommandParser {
    /**
     * Parses the given input string to produce a Command object.
     *
     * @param input The input string from the user.
     * @return The Command object corresponding to the user's input.
     * @throws Exception if the command cannot be parsed or is unknown.
     */
    public static Command parse(String input) throws Exception {
        Matcher instructionExtractor = extractInstructionAndInformation(input);

        String instructionTag = instructionExtractor.group("instructionTag").trim();
        String information = instructionExtractor.group("information").trim();

        Identifier instruction = getInstruction(instructionTag);

        return createCommand(instruction, information);
    }

    /**
     * Extracts the instruction and additional information from the input string.
     *
     * @param input The input string from the user.
     * @return A Matcher object containing the extracted instruction and information.
     * @throws UnknownCommandException if the command cannot be parsed or is unknown.
     */
    private static Matcher extractInstructionAndInformation(String input) throws UnknownCommandException {
        Matcher instructionExtractor = Pattern.compile("(?<instructionTag>\\S++)(?<information>.*)")
                .matcher(input.trim());

        if (!instructionExtractor.matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_ERROR);
        }

        return instructionExtractor;
    }

    private static Identifier getInstruction(String instructionTag) throws UnknownCommandException {
        try {
            return Identifier.valueOf(instructionTag.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }

    private static Command createCommand(Identifier identifier, String information) throws UnknownCommandException {
        switch (identifier) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case HELP:
            return ParserHelper.parseHelpCommand(information);
        case MARK:
            return ParserHelper.parseMarkCommand(information);
        case UNMARK:
            return ParserHelper.parseUnmarkCommand(information);
        case DELETE:
            return ParserHelper.parseDeleteCommand(information);
        case TODO:
            return ParserHelper.parseTodoCommand(information);
        case DEADLINE:
            return ParserHelper.parseDeadlineCommand(information);
        case EVENT:
            return ParserHelper.parseEventCommand(information);
        case FIND:
            return ParserHelper.parseFindCommand(information);
        default:
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }
}
