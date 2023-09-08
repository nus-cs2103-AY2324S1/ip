package bot.utils.commands;

import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Command to indicate the end of the program.
 */
class ExitCommand extends Command {

    /**
     * Checks if the bot should exit after the execution of the command.
     *
     * @return True if the bot should exit, false otherwise.
     */
    public boolean getExitStatus() {
        return true;
    }

    /**
     * Executes the command.
     *
     * @param tasks   Bot.Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Bot.Storage for storing data.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}
