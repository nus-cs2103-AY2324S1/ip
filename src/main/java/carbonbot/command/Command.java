package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.exception.CarbonException;

/**
 * This abstract class is the superclass of all classes representing a command that CarbonBot supports.
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructs a Command that should not cause the bot to exit.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Constructs a Command that might exit depending on the argument.
     *
     * @param isExit Whether the command should cause the bot to exit
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Whether the command should cause the bot to exit.
     *
     * @return Boolean value if the bot should exit after the command is executed
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes this command. The command might interact with the TaskList, Ui, and Storage.
     *
     * @param tasks   Task List
     * @param ui      UI
     * @param storage Storage
     * @throws CarbonException Any error that occurs due to the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CarbonException;
}
