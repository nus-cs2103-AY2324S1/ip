package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private Integer taskIndexToDelete;

    /**
     * Constructor for DeleteCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public DeleteCommand(Map<String, String> parameterMap) throws DukeException {
        super(parameterMap);

        this.loadParameters();
        this.checkIfParametersSpecified();
        this.checkIfParametersValid();
    }

    @Override
    protected void loadParameters() throws DukeException {
        try {
            taskIndexToDelete = Integer.parseInt(parameterMap.get("default")) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                    + "Please retry with a valid task number.", parameterMap.get("default")));
        }
    }

    @Override
    protected void checkIfParametersSpecified() throws DukeException {
        if (taskIndexToDelete == null) {
            throw new DukeException("Please enter a task number to delete.");
        }
    }

    @Override
    protected void checkIfParametersValid() throws DukeException {
        if (taskIndexToDelete < 0) {
            throw new DukeException("Task number cannot be negative.\n"
                    + "Please retry with a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {

        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        if (taskIndexToDelete >= tasks.size()) {
            throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                    taskIndexToDelete + 1,
                    tasks.size()));
        }

        Task deletedTask = tasks.deleteTask(taskIndexToDelete);
        tasks.storeTasks(storage);

        StringBuilder response = new StringBuilder("Noted. I've removed this task:\n");
        response.append(String.format("  %s\n", deletedTask.toString()));
        response.append(String.format("Now you have %d tasks in the list.", tasks.size()));

        return response.toString();
    }
}

