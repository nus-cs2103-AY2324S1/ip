package duke.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

/**
* A parser that parse the input String into a Duke Instruction with respective information encapsulated.
*/
public class CommandParser {
    /**
     * An Instruction enum that encapsulates all types of Instruction.
     */
    public enum Instruction {
        BYE,
        LIST,
        HELP,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }


    public static Command parse(String input) throws Exception {
        Matcher instructionExtractor = extractInstructionAndInformation(input);
        String instructionTag = instructionExtractor.group("instructionTag").trim();
        String information = instructionExtractor.group("information").trim();

        Instruction instruction = matchInstructionTag(instructionTag);
        return createCommand(instruction, information);
    }

    private static Matcher extractInstructionAndInformation(String input) throws UnknownCommandException {
        Matcher instructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.trim());
        //@@author

        // Check if the input matches the regular expression
        if (!instructionExtractor.matches()) {
            // If not, throw an exception
            throw new UnknownCommandException(ErrorMessages.EMPTY_ERROR);
        }
        // Return the Matcher object that contains the instruction tag and information
        return instructionExtractor;
    }

    private static Instruction matchInstructionTag(String instructionTag) throws UnknownCommandException {
        try {
            //convert instruction tag to uppercase to match enum
            return Instruction.valueOf(instructionTag.toUpperCase());
        } catch (IllegalArgumentException e) {
            //thrown if instruction tag does not match any of the enum values
            throw new UnknownCommandException(ErrorMessages.UNRECOGNIZED_ERROR);
        }
    }

    private static Command createCommand(Instruction instruction, String information)
            throws UnknownCommandException {
        switch (instruction) {
        case BYE:
            //create and return ExitCommand
            return new ExitCommand();
        case LIST:
            //create and return ListCommand
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
