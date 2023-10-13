package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

/**
 * Represents a command to delete a task
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Represents a constructor of the DeleteCommand object
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException {
        String output = "";
        output += ui.deleteTask(tasks, taskNumber);
        return output;
    }
}
