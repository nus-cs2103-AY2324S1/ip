package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.exceptions.TaskNotDoneException;
import martin.task.Task;

import java.util.ArrayList;

public class UnmarkCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public UnmarkCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Unmarks the task at the specified index, marking it as not done.
    * @return String A message indicating the task has been unmarked or an error message.
    */
    @Override
    public String execute() throws MartinException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
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
