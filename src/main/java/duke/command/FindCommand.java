package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Searches through the task list and displays tasks matching the keyword.
 */
public class FindCommand implements Command {
    private final String details;

    /**
     * Constructs a FindCommand with the specified keyword details.
     *
     * @param details The keyword to search for in the task names.
     */
    public FindCommand(String details) {
        this.details = details;
    }

    /**
     * Executes the command by searching and displaying tasks containing the keyword.
     * Displays the matching tasks to the user.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(details)) {
                output.append("\n").append(i + 1).append(".").append(tasks.get(i).toString());
            }
        }
        ui.sendMessage(output.toString());
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return `false` indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
