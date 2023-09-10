package duke.command.task;

import java.util.Map;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.object.task.Task;
import duke.storage.Storage;
import duke.util.Formatter;

/**
 * Command to add a new task.
 */
public abstract class TaskCommand extends Command {

    /**
     * Constructs TaskCommand.
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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task newTask = this.makeTask();
        tasks.add(newTask);
        return String.format("Got it. I've added this task:\n  %s\n%s",
                tasks.access(tasks.size()).toString(), Formatter.getTaskCount(tasks.size()));
    }

}
