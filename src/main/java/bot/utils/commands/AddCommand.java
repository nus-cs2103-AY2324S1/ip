package bot.utils.commands;

import bot.exceptions.InvalidTaskException;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;
import bot.utils.tasks.Task;

/**
 * Bot.Command to add tasks to the task list.
 */
class AddCommand extends Command {
    /**
     * Full command string
     */
    private final String input;

    /**
     * Creates an AddCommand with the full command string.
     *
     * @param input Full command string.
     */
    protected AddCommand(String input) {
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
     * @param tasks   Bot.Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Bot.Storage for storing data.
     * @return Bot's response to the command.
     * @throws InvalidTaskException If the command adds a task and fails to do so.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException {
        Task newTask = Task.makeTask(input);
        tasks.add(newTask);
        return ui.println("Added:\n" + newTask.toString())
                .concat(ui.println("Now you have " + tasks.size() + " task(s) in the list."));
    }
}
