package dre.command;

import dre.exception.DreException;
import dre.storage.Storage;
import dre.task.Deadline;
import dre.task.Event;
import dre.ui.Ui;
import dre.task.Task;
import dre.task.TaskList;

/**
 * Represents a command to edit a task.
 */
public class EditCommand extends Command {
    private final int TASK_NUMBER;
    private final String field;
    private final String newValue;

    /**
     * Creates an EditCommand with the specified task index within the task list.
     *
     * @param TASK_NUMBER The index of the task within the task list to be edited.
     * @param field The value type that is to be modified
     * @param newValue The new value to refactor the task.
     */
    public EditCommand(int TASK_NUMBER, String field, String newValue) {
        this.TASK_NUMBER = TASK_NUMBER;
        this.field = field;
        this.newValue = newValue;
    }

    /**
     * Executes the edit command, editing a task in the task list.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DreException {
        Task task = tasks.getTask(TASK_NUMBER);
        switch (field) {
            case "description":
                task.editDescription(newValue);
                break;
            case "byDate":
                ((Deadline) task).editByDate(newValue);
                break;
            case "fromDate":
                ((Event) task).editFromDate(newValue);
                break;
            case "toDate":
                ((Event) task).editToDate(newValue);
                break;
            default:
                throw new DreException("Use this format:\n edit {task index} " +
                        "{description / fromDate / toDate / byDate} {new description / yyyy-MM-dd}");
        }
        return ui.generateEditSuccessMessage(task);
    }
}