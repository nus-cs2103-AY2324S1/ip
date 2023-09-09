package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.exceptions.TaskAlreadyDoneException;
import martin.task.Task;

import java.util.ArrayList;

public class MarkCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public MarkCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Marks the task at the specified index as done.
    * @return String A message indicating the task has been marked as done or an error message.
    */
    @Override
    public String execute() throws MartinException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
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
