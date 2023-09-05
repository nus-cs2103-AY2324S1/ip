package main.logic.command;

import java.util.List;
import java.util.Map;

import main.KniazSession;
import main.logic.handler.CommandHandler;
import ui.inputparser.InstructionType;


/**
 * Represents a command to be executed within the Kniaz application.
 * A KniazCommand encapsulates the necessary information to execute a command,
 * including its instruction type, command handler, and arguments.
 */
public class KniazCommand {

    private CommandHandler commandHandler;
    private List<String> unnamedArgs;
    private Map<? extends String, ? extends String> namedArgs;
    private InstructionType instruct;

    /**
     * Constructs a new KniazCommand with the provided parameters.
     *
     * @param instruct       The type of instruction associated with this command.
     * @param commandHandler The handler responsible for executing this command.
     * @param unnamedArgs    A list of unnamed arguments for the command.
     * @param namedArgs      A map of named arguments (key-value pairs) for the command.
     */
    public KniazCommand(InstructionType instruct,
                        CommandHandler commandHandler,
                        List<String> unnamedArgs,
                        Map<? extends String, ? extends String> namedArgs) {
        this.instruct = instruct;
        this.commandHandler = commandHandler;
        this.unnamedArgs = unnamedArgs;
        this.namedArgs = namedArgs;
    }

    /**
     * Executes the command using the provided KniazSession.
     *
     * @param session The session in which the command is executed.
     * @return The result of executing the command.
     */
    public String execute(KniazSession session) {
        return commandHandler.handle(session, this.unnamedArgs, this.namedArgs);
    }

    /**
     * Retrieves the instruction type associated with this command.
     *
     * @return The instruction type of the command.
     */
    public InstructionType getInstruct() {
        return this.instruct;
    }
}
