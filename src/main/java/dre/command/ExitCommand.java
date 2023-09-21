package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, showing goodbye message and saving tasks.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to save tasks before exiting.
     */
    @Override
    public String execute(TaskList tasks,
                          Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.showGoodbye();
    }
}
