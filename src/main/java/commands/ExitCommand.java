package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Initializes a new ExitCommand instance.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the exit command, saving tasks to a file and displaying a goodbye message.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasksToFile(taskList.getList());
        return ui.showGoodbyeMessage();
    }
}
