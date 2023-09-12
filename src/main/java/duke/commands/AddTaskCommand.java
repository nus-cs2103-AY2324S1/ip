package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDetailsOfTaskError;
import duke.exception.UnknownCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class AddTaskCommand extends Command {
    private String command;

    /**
     * Constructor of the AddTaskCommand object
     */
    public AddTaskCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Execute the AddTaskCommand and returns a string
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws EmptyDetailsOfTaskError, UnknownCommandException {
        String output = "";
        try {
            Task currentTask = Task.createTask(command);
            tasks.addTask(currentTask);
            output += ui.addTask(currentTask, tasks);
        } catch (EmptyDetailsOfTaskError e) {
            output += e.getMessage();
            System.out.println(e.getMessage());
        } catch (UnknownCommandException e) {
            output += e.getMessage();
            System.out.println(e.getMessage());
        }
        return output;
    }
}
