package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.DukeList;

/**
 * The AddCommand class represents a command for adding tasks to the task list.
 *
 */
public class AddCommand extends Command {
    private String taskType;

    private String description;

    /**
     * Constructs an AddCommand with the specified task type and command details.
     *
     * @param taskType The type of task to be added.
     * @param description The details of the task to be added.
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Executes the AddCommand by adding a task to the task list and saving it to storage.
     *
     * @param dukeList The task list to which the task will be added.
     * @param storage  The storage object used for saving tasks.
     * @throws DukeException If there is an error adding the task.
     */
    @Override
    public String execute(DukeList dukeList, Storage storage) throws DukeException {
        Task taskToAdd;
        try {
            assert taskType != null : "Task type cannot be null";
            assert description != null : "Description of task cannot be null";
            taskToAdd = dukeList.addTask(this.taskType, this.description);
            storage.saveData(dukeList.getList());
            assert taskToAdd != null : "Task to be added cannot be null";
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return Ui.showList(taskToAdd, dukeList.getList());
    }

}