package bot.utils.commands;

import bot.enums.DoneStatus;
import bot.exceptions.InvalidIndexException;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Bot.Command to mark the task as done or not done.
 */
class MarkCommand extends Command {
    /**
     * Index to mark the task at.
     */
    private final int index;
    /**
     * Mark the task as done or not done.
     */
    private final DoneStatus done;

    /**
     * Creates a MarkCommand with the command to mark or unmark the task
     * at the given index.
     *
     * @param index Index to mark the task at.
     * @param done  Mark the task as done or not done.
     */
    protected MarkCommand(int index, DoneStatus done) {
        this.index = index;
        this.done = done;
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
        if (done.equals(DoneStatus.DONE)) {
            tasks.mark(index);
            return ui.println("I'll mark this as done:\n" + tasks.get(index).toString());
        } else {
            tasks.unmark(index);
            return ui.println("I'll mark this as not done:\n" + tasks.get(index).toString());
        }
    }
}
