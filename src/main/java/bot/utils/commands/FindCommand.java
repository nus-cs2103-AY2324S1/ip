package bot.utils.commands;

import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Command for finding specific tasks in the task list.
 */
class FindCommand extends Command {
    /**
     * Full command string.
     */
    private final String input;

    /**
     * Creates an FindCommand with the full command string.
     *
     * @param input Full command string.
     */
    protected FindCommand(String input) {
        this.input = input;
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
     * @param tasks   Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Storage for storing data.
     * @return Bot's response to the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList queries = tasks.findAll(input);
        if (queries.size() > 0) {
            return ui.println("Here are the matching tasks in your list:")
                    .concat(ui.displayTaskList(queries));
        } else {
            return ui.println("Sorry, no matching tasks found.");
        }
    }
}
