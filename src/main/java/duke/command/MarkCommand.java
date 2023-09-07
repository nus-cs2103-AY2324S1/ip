package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to mark the specified task as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private static final String COMMAND_RESPONSE = "Nice! I've marked this task as done:\n\t";

    private int taskIndex;

    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.mark(taskIndex);
        storage.save(tasks);
        return COMMAND_RESPONSE + task;
    }
}
