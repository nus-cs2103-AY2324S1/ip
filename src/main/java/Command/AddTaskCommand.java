package Command;
import DukeException.FailureInExecuteException;
import Duke.TaskList;
import Duke.UserInterface;
import Task.Task;

import java.io.IOException;

public class AddTaskCommand implements Commandable {
    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            list.addTask(task);
        } catch(IOException e) {
            throw new FailureInExecuteException(e.getMessage());
        }
        return false;
    }
}
