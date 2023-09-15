package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that handles the deletion of tasks from the task list.
 */
public class DeleteCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public DeleteCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
     * Deletes the task at the specified index provided by the command.
     *
     * @return A confirmation message of the deleted task.
     * @throws InvalidTaskNumberException If the specified task number is invalid or out of range.
     * @throws MartinException If there's any other error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Task number out of range.");
            }
            
            Task removedTask = tasks.remove(taskNo - 1);
            return "Noted. I've removed this task:\n       " + removedTask + "\n     Now you have " + tasks.size() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}
