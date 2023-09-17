package duke.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

public class CommandParser {
    public static Command parse(String input) throws Exception {
        Matcher instructionExtractor = extractInstructionAndInformation(input);
        String instructionTag = instructionExtractor.group("instructionTag").trim();
        String information = instructionExtractor.group("information").trim();

        CommandType instruction = matchFlag(instructionTag);
        return createCommand(instruction, information);
    }

    private static Matcher extractInstructionAndInformation(String input) throws UnknownCommandException {
        Matcher instructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.trim());

        if (!instructionExtractor.matches()) {
            throw new UnknownCommandException(ErrorMessages.EMPTY_ERROR);
        }

        return instructionExtractor;
    }

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
        default:
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }
}
