package commands;

import java.io.IOException;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;


/**
 * Represents a type of command that can be read by the chatbot.
 */
public class UnmarkComment extends Command {

    private final int index;

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
    public String execute(TaskList tasks, DataFile dF) throws DukeException {
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is empty, nothing to unmark");
        }
        Task selTask = tasks.getTask(index);
        selTask.taskNotCompleted();
        try {
            dF.editFileAtLineN(index, '0');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cmdToString(selTask.toString());
    }

    /**
     * Returns the string representation of unmark command.
     * @param task String representation of Task.
     * @return String representation of unmark command.
     */
    public String cmdToString(String task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
