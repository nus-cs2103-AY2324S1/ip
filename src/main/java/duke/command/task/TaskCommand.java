package duke.command.task;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.object.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Command to add a new task.
 */
public abstract class TaskCommand extends Command {

    /**
     * Constructor for TaskCommand.
     * 
     * @param commandName The name of the command.
     * @param args The arguments entered by the user.
     */
    public TaskCommand(String commandName, Map<String, Object> args) {
        super(commandName, args);
    }

    protected abstract Task makeTask() throws DukeException;

    /**
     * @inheritdoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = this.makeTask();
        tasks.add(newTask);
        ui.print(String.format("Got it. I've added this task:\n  %s\n%s",
                tasks.access(tasks.size()).toString(), ui.getTaskCount(tasks.size())));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
