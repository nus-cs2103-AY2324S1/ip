package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.exceptions.TaskAlreadyDoneException;
import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that to mark a task at a specified index.
 */
public class MarkCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates a Mark Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public MarkCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Marks the task at the specified index as done.
     * 
     * @return String A message indicating the task has been marked as done or an error message.
     * @throws InvalidTaskNumberException If an invalid task number is given.
     * @throws TaskAlreadyDoneException If the task to be marked is already marked as done.
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
            if (task.isDone()) {
                throw new TaskAlreadyDoneException("Task \"" + task.getDescription() + "\" is already done.");
            }

            task.markAsDone();
            return "Nice! I've marked this task as done:\n       " + task;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
