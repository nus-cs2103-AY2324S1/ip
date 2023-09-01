package command;

import duke.Storage;
import duke.Ui;

import task.TaskList;

/**
 * Exits the Duke chatbot application
 */
public class ExitCommand extends Command {

    /**
     * Exits the Duke chatbot
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        setExit(true);
    }
}
