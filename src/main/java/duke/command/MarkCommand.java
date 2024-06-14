package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private Integer taskIndexToMark;

    /**
     * Constructor for MarkCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public MarkCommand(Map<String, String> parameterMap) throws DukeException {
        super(parameterMap);

        this.loadParameters();
        this.checkIfParametersSpecified();
        this.checkIfParametersValid();
    }

    @Override
    protected void loadParameters() throws DukeException {
        try {
            taskIndexToMark = Integer.parseInt(parameterMap.get("default")) - 1;
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
        if (taskIndexToMark == null) {
            throw new DukeException("Please enter a task number.");
        }
    }

    @Override
    protected void checkIfParametersValid() throws DukeException {
        if (taskIndexToMark < 0) {
            throw new DukeException("Task number cannot be negative.\n"
                    + "Please retry with a valid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        if (taskIndexToMark >= tasks.size()) {
            throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                    taskIndexToMark + 1,
                    tasks.size()));
        }

        Task taskMarked = tasks.markAsDone(taskIndexToMark);
        tasks.storeTasks(storage);

        assert taskMarked != null : "Marked task should not be null";

        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as done:\n");
        response.append(String.format("  %s", taskMarked.toString()));

        return response.toString();
    }
}
