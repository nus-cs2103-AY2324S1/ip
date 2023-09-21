package bot.utils.commands;

import bot.exceptions.EmptyListException;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Bot.Command to list all tasks in the task list.
 */
class ListCommand extends Command {
    /**
     * Checks if the bot should exit after the execution of the command.
     *
     * @return True if the bot should exit, false otherwise.
     */
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks   Bot.Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Bot.Storage for storing data.
     * @return Bot's response to the command.
     * @throws EmptyListException If an illegal operation is performed on an empty list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
        return ui.println("Here are the tasks in your list:").concat(ui.displayTaskList(tasks));
    }
}
