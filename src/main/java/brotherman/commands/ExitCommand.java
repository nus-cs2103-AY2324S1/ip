package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command to exit the program
     * @param tasks Task list to be added to
     * @param ui Ui to show the user the task has been added
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveToFile(tasks.list());
        ui.showGoodbyMessage();
    }
}
