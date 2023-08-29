package ui.inputparser.commands;

import commandhandling.*;
import exceptions.syntax.KniazFormatException;
import exceptions.syntax.KniazInvalidArgsException;
import exceptions.syntax.KniazInvalidCommandException;
import main.KniazSession;
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

    public static KniazCommand<? extends CommandHandler> parseCommand(InstructionType instruction,
                                                                      String... args) throws KniazInvalidArgsException,
                                            KniazInvalidCommandException {
        if (instruction == InstructionType.INVALID) {
            throw new KniazInvalidCommandException();
        }

        if (instruction.numArgs != args.length) {
            throw new KniazInvalidArgsException();
        }

        for (String arg : args) {
            Matcher argPatternMatcher = instruction.argPattern.matcher(arg);
            if (!argPatternMatcher.matches()) {
                throw new KniazFormatException();
            }
        }

        // Guaranteed at this point command is valid

        return new KniazCommand<CommandHandler>() {
            @Override
            public String execute(CommandHandler handler, KniazSession session, String[] args) {
                return handler.handle(session, args);
            }
        };



    }


}
