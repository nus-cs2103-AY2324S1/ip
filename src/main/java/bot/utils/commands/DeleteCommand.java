package bot.utils.commands;

import bot.exceptions.InvalidIndexException;
import bot.utils.Storage;
import bot.utils.tasks.Task;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Command to delete tasks.
 */
class DeleteCommand extends Command {
    /**
     * Index to delete task at.
     */
    private final int index;

    /**
     * Creates a DeleteCommand with the given index.
     *
     * @param index Index to delete task at.
     */
    protected DeleteCommand(int index) {
        this.index = index;
    }

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
     * @throws InvalidIndexException If the command tries to access an invalid index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task task = tasks.remove(index);
        return ui.println("I've removed this task:\n" + task.toString())
                .concat(ui.println("Now you have " + tasks.size() + " task(s) in the list."));
    }
}
