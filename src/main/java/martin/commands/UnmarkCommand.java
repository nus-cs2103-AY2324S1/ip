package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.exceptions.TaskNotDoneException;
import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that to unmark a task at a specified index.
 */
public class UnmarkCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates an Unmark Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public UnmarkCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Unmarks the task at the specified index, implying that it is not done.
     * 
     * @return A string indicating the task has been marked as done or an error message.
     * @throws InvalidTaskNumberException If an invalid task number is given.
     * @throws TaskNotDoneException If the task to be unmarked is already marked as not done.
     * @throws MartinException If there's any other error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        try {
            int taskNo = Integer.parseInt(input.split(" ")[1]);
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }

            Task task = tasks.get(taskNo - 1);
            if (!task.isDone()) {
                throw new TaskNotDoneException("Task \"" + task.getDescription() + "\" is not done yet.");
            }

            task.unmarkAsDone();
            return "OK, I've marked this task as not done yet:\n       " + task;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
