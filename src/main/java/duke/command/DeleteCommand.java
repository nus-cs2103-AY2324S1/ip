package duke.command;

import duke.Ui;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.TaskIndexOutOfBoundsException;
import duke.Storage;
import duke.task.*;

/**
 * Deletes a task from the taskList
 */
public class DeleteCommand extends Command {
    protected static final String regex = "^delete\\s([\\w\\s]*)$";
    public DeleteCommand(String response) {
        super(response, regex);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException {
        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new InvalidCommandException(
                    "Invalid input. Usage: delete <task_index>"
            );
        }
        int taskIndex = Integer.parseInt(matcher.group(1));

        if (taskIndex < 1 || taskIndex > taskList.getSize()) {
            throw new InvalidCommandException("Invalid task index");
        }

        taskList.deleteTask(taskIndex-1);
        return "Task successfully deleted";

    }
}
