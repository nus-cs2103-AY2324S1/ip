package kniaz.logic.command;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import kniaz.logic.handler.CommandHandler;
import kniaz.logic.handler.DeadlineHandler;
import kniaz.logic.handler.DeleteHandler;
import kniaz.logic.handler.EventHandler;
import kniaz.logic.handler.FindHandler;
import kniaz.logic.handler.InvalidHandler;
import kniaz.logic.handler.ListHandler;
import kniaz.logic.handler.MarkHandler;
import kniaz.logic.handler.QuitHandler;
import kniaz.logic.handler.ToDoHandler;
import kniaz.logic.handler.UnmarkHandler;
import ui.inputparser.InstructionType;


/**
 * Class encapsulating a command given to kniaz.Kniaz,
 * should only be instantiated via KniazParser when it parses a command
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
                    InstructionType.FIND, new FindHandler(),
                    InstructionType.INVALID, new InvalidHandler()
            ));

    static {
        // This ensures that at compile time all instruction types have an explicit, valid handler
        for (InstructionType i : InstructionType.values()) {
            assert(INSTRUCT_TO_HANDLER.containsKey(i))
                    : "There was a command " + i.alias + "with no associated handler!";
        }
    }

    /**
     * Makes a new KniazCommand from the provided instruction and arguments
     * @param instruction the instruction type the command is to have
     * @param unnamedArgs the unnamed arguments supplied to the commmand
     * @param namedArgs the named arguments supplied to the command
     * @return the KniazCommand encapsulating the instruction + arguments
     */
    public static KniazCommand makeCommand(InstructionType instruction,
                                           List<String> unnamedArgs,
                                           Map<? extends String, ? extends String> namedArgs) {
        assert INSTRUCT_TO_HANDLER.containsKey(instruction)
                : "Tried to find a handler for a command that has unknown mapping!";

        CommandHandler handler = INSTRUCT_TO_HANDLER.get(instruction);
        return new KniazCommand(instruction, handler, unnamedArgs, namedArgs);

        // Guaranteed at this point command is valid





    }


}
