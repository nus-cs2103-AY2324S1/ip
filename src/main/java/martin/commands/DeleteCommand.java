package martin.commands;

import martin.Ui;
import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.task.Task;

import java.util.ArrayList;

public class DeleteCommand implements Command {

    private Ui ui;
    private String command;
    private ArrayList<Task> tasks;

    public DeleteCommand(String command, ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Deletes the task at the specified index provided by the command.
    */
    @Override
    public void execute() throws MartinException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Task number out of range.");
            }
            
            Task removedTask = tasks.remove(taskNo - 1);
            ui.printMessage("Noted. I've removed this task:\n       " + removedTask + "\n     Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}
