package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private Integer taskIndexToUnmark;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public UnmarkCommand(Map<String, String> parameterMap) throws DukeException {
        super(parameterMap);

        this.loadParameters();
        this.checkIfParametersSpecified();
        this.checkIfParametersValid();
    }

    @Override
    protected void loadParameters() throws DukeException {
        try {
            taskIndexToUnmark = Integer.parseInt(parameterMap.get("default")) - 1;
        } catch (NumberFormatException e) {
            if (parameterMap.get("default") == null) {
                throw new DukeException("Please enter a task number.");
            }

            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                    + "Please retry with a valid task number.", parameterMap.get("default")));
        }
    }

    @Override
    protected void checkIfParametersSpecified() throws DukeException {
        if (taskIndexToUnmark == null) {
            throw new DukeException("Please enter a task number.");
        }
    }

    @Override
    protected void checkIfParametersValid() throws DukeException {
        if (taskIndexToUnmark < 0) {
            throw new DukeException("Task number cannot be negative.\n"
                    + "Please retry with a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        if (taskIndexToUnmark >= tasks.size()) {
            throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                    taskIndexToUnmark + 1,
                    tasks.size()));
        }

        Task unmarkedTask = tasks.markAsUndone(taskIndexToUnmark);
        tasks.storeTasks(storage);

        assert unmarkedTask != null : "Unmarked task should not be null";

        StringBuilder response = new StringBuilder();
        response.append("OK, I've marked this task as not done yet:\n");
        response.append(String.format("  %s", unmarkedTask.toString()));

        return response.toString();
    }
}
