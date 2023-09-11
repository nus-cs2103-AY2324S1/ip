package commands;

import java.io.IOException;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;


/**
 * Represents a type of command that can be read by the chatbot.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * DeleteCommand constructor that takes in an int.
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public String execute(TaskList tasks, DataFile dF) throws DukeException {
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is already empty, nothing to delete");
        }
        Task task = tasks.remTask(index);
        try {
            dF.deleteTaskFromFile(index);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cmdToString(task.toString(), tasks.getSize());
    }

    /**
     * Returns the string representation of delete command.
     * @param task String representation of Task.
     * @param size Size of the taskList.
     * @return String representation of delete command.
     */
    public String cmdToString(String task, int size) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }
}
