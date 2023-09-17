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
        newTask.execute(list);
        list.swap(taskNumber - 1, list.getSize() - 1);
        list.deleteTaskAtIndex(list.getSize() - 1);
        return "Updated task!";
    }
}
