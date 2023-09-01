package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;
/**
 * AddTaskCommand represents a command that adds a task to the list.
 */
public class AddTaskCommand implements Commandable {
    private Task task;

    /**
     * Sets the task to be added upon execution.
     * @param task the task to be added.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the adding of the task to the list provided, and provides outputs for the interface to print.
     * @param list the list that receives the task.
     * @param ui the interface that prints out any necessary outputs.
     * @return false, since the execution does not end the bot.
     * @throws FailureInExecuteException when there is a failure in writing to the list or its associated file.
     */
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            list.addTask(task);
        } catch (IOException e) {
            throw new FailureInExecuteException(e.getMessage());
        }
        ui.output("Added successfully!");
        return false;
    }
}
