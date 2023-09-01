package commands;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class MarkCommand extends Command{
    private Task task;
    private final int index;

    /**
     * MarkCommand constructor that takes in an int.
     * @param index Index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks, DataFile dF) throws DukeException{
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is empty, nothing to mark");
        }
        Task task = tasks.getTask(index);
        task.taskCompleted();
        this.task = task;
        System.out.println(this);
        try {
            dF.editFileAtLineN(index, '1');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the string representation of mark command.
     * @return String representation of mark command.
     */
    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + task;
    }
}
