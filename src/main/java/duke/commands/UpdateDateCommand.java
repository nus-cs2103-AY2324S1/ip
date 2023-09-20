package duke.commands;

import java.time.LocalDateTime;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to update date fields in a task.
 */
public class UpdateDateCommand extends Command {

    private int taskIndex;
    private String fieldToUpdate;
    private LocalDateTime updatedDate;


    /**
     * Creates UpdateDateCommand object with specified index of task to be updated.
     *
     * @param taskIndex Index of task to be updated.
     * @param fieldToUpdate Field to be updated.
     * @param updatedDate New date.
     */
    public UpdateDateCommand(int taskIndex, String fieldToUpdate, LocalDateTime updatedDate) {
        this.taskIndex = taskIndex;
        this.fieldToUpdate = fieldToUpdate;
        this.updatedDate = updatedDate;
    }

    /**
     * Executes updateDate command. Updates a date field in a task, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui    The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.updateDate(this.taskIndex, fieldToUpdate, this.updatedDate);
        store.saveTasks(tasks);
        return ui.showUpdate(tasks.getTask(this.taskIndex));
    }
}
