package bot.command;

import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;

import java.io.IOException;

public abstract class Command {

    public static final String SPACER = "________________________________________";

    /**
     * Execute a series of instructions depending on the type of Command
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    public abstract String execute() throws FileErrorBotException, IOException;
}
