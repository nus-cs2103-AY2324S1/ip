package bot.command;

import bot.exception.FileErrorBotException;


public abstract class Command {

    public static final String SPACER = "________________________________________";

    /**
     * Execute a series of instructions depending on the type of Command and return
     * the output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    public abstract String execute() throws FileErrorBotException;
}
