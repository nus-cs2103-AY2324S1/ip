package commands;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class UnmarkComment extends Command {

    private final int index;
    private Task task;

    /**
     * DeleteCommand constructor that takes in an int.
     * @param index Index of the task to be unmarked.
     */
    public UnmarkComment(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks, DataFile dF) throws DukeException {
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is empty, nothing to unmark");
        }
        Task selTask = tasks.getTask(index);
        selTask.taskNotCompleted();
        task = selTask;
        System.out.println(this);
        try {
            dF.editFileAtLineN(index, '0');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the string representation of unmark command.
     * @return String representation of unmark command.
     */
    @Override
    public String toString() {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
