package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;

/**
 * Updates a task in the list with other details.
 */
public class UpdateCommand extends Command {
    private int taskNumber;
    private Command newTask;

    /**
     * Creates a new UpdateCommand that updates a task in the TaskList with different details.
     * @param taskNumber The index of the task to be updated.
     * @param newTask The corresponding command call of the updated task.
     */
    public UpdateCommand(int taskNumber, Command newTask) {
        this.taskNumber = taskNumber;
        this.newTask = newTask;
    }
    @Override
    public String execute(TaskList list) throws DukeException {
        int listSize = list.getSize();
        if (this.taskNumber - 1 >= listSize) {
            throw new DukeException("Index out of range!");
        }
        newTask.execute(list);
        list.swap(this.taskNumber - 1, listSize);
        list.deleteTaskAtIndex(list.getSize() - 1);
        return "Updated task!";
    }
}
