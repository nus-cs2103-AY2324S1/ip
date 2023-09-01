package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;

public class AddTaskCommand implements Commandable {
    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            list.addTask(task);
        } catch (IOException e) {
            throw new FailureInExecuteException(e.getMessage());
        }
        return false;
    }
}
