package duke.commands;

import duke.Storage;
import duke.UI;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor of the DeleteCommand object
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Execute the DeleteCommand and returns a string
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        String output = "";
        output += ui.deleteTask(tasks, taskNumber);
        return output;
    }
}
