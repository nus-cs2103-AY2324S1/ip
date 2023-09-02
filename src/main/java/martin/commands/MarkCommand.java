package martin.commands;

import martin.Ui;
import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.exceptions.TaskAlreadyDoneException;
import martin.task.Task;

import java.util.ArrayList;

public class MarkCommand implements Command {

    private Ui ui;
    private String command;
    private ArrayList<Task> tasks;

    public MarkCommand(String command, ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Marks the task at the specified index as done.
    */
    @Override
    public void execute() throws MartinException {
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
            ui.printMessage("Nice! I've marked this task as done:\n       " + task);
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
    }
}
