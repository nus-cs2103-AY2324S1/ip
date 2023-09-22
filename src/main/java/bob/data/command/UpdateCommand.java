package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;

public class UpdateCommand extends Command {
    private int taskNumber;
    private Command newTask;
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
