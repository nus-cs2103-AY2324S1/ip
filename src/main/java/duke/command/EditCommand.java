package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for adding a task to the task list.
 */
public class EditCommand extends Command {
    private int index;
    private String newDescription;

    /**
     * Initializes an EditCommand with the index of the task to be edited and the new description.
     *
     * @param index The index of the task to be edited.
     * @param newDescription The new description to be set to.
     */
    public EditCommand(int index, String newDescription) {
        this.index = index;
        this.newDescription = newDescription;
    }

    /**
     * Executes the command to edit the description of a specified task.
     *
     * @param taskList The task list where the task will be added.
     * @param storage  The storage component for saving tasks after adding (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @return A message indicating that the task has been edited.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Task taskToBeEdited = taskList.getTask(index);
        taskToBeEdited.editDescription(newDescription);
        storage.saveTasks(taskList);
        return ui.editTaskMessage(taskToBeEdited);
    }
}
