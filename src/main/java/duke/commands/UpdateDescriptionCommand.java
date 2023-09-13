package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to update description of a task.
 */
public class UpdateDescriptionCommand extends Command {

    private int taskIndex;
    private String updateInformation;

    /**
     * Creates UpdateDescriptionCommand object with specified index of task to be updated.
     *
     * @param taskIndex Task to be added.
     * @param updateInformation New Description.
     */
    public UpdateDescriptionCommand(int taskIndex, String updateInformation) {
        this.taskIndex = taskIndex;
        this.updateInformation = updateInformation;
    }

    /**
     * Executes updateDescription command. Updates description of a task, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui    The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.updateDescription(this.taskIndex, this.updateInformation);
        store.saveTasks(tasks);
        return ui.showUpdate(tasks.getTask(this.taskIndex));
    }
}
