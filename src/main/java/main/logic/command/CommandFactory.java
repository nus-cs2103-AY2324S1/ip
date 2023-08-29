package main.logic.command;


import exceptions.syntax.KniazInvalidArgsException;

import main.logic.handler.*;
import ui.inputparser.InstructionType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class encapsulating a command given to Kniaz, should only be instantiated via KniazParser when it parses a command
 */
public abstract class CommandFactory {
    private static final EnumMap<InstructionType, CommandHandler> INSTRUCT_TO_HANDLER =
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

    public static KniazCommand makeCommand(InstructionType instruction,
                                           List<String> unnamedArgs,
                                           Map<? extends String, ? extends  String> namedArgs){


        CommandHandler handler = INSTRUCT_TO_HANDLER.get(instruction);

        return new KniazCommand(handler, unnamedArgs, namedArgs);

        // Guaranteed at this point command is valid





    }


}
