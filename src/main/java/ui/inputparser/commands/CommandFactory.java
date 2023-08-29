package ui.inputparser.commands;

import commandhandling.*;
import exceptions.syntax.KniazInvalidArgsException;
import ui.inputparser.InstructionType;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Class encapsulating a command given to Kniaz, should only be instantiated via KniazParser when it parses a command
 */
public abstract class CommandFactory {
    private static final EnumMap<InstructionType,CommandHandler> INSTRUCT_TO_HANDLER =
            new EnumMap<>(Map.of(
                    InstructionType.TODO, new ToDoHandler(),
                    InstructionType.DEADLINE, new DeadlineHandler(),
                    InstructionType.EVENT, new EventHandler(),
                    InstructionType.MARK, new MarkHandler(),
                    InstructionType.UNMARK, new UnmarkHandler(),
                    InstructionType.LIST, new ListHandler(),
                    InstructionType.QUIT, new QuitHandler(),
                    InstructionType.DELETE, new DeleteHandler(),
                    InstructionType.INVALID, new InvalidHandler()
            ));

    public static KniazCommand<? extends CommandHandler> makeCommand(InstructionType instruction,
                                                                     String... args) throws KniazInvalidArgsException {
        if (instruction.numArgs != args.length) {
            throw new KniazInvalidArgsException();
        }

        for (String arg : args) {
            Matcher argPatternMatcher = instruction.argPattern.matcher(arg);
            if (!argPatternMatcher.matches()) {
                throw new KniazInvalidArgsException();
            }
        }

        CommandHandler handler = INSTRUCT_TO_HANDLER.get(instruction);

        return new KniazCommand<CommandHandler>(handler, args);

        // Guaranteed at this point command is valid





    }


}
