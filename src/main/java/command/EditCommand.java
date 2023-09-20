package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.Objects;

/**
 * Represents a command to edit a task's properties, either by marking it as
 * complete or incomplete.
 */
public class EditCommand extends Command {
    private final int taskIndex;
    private final String description;

    /**
     * Initializes an EditCommand with the given description and task index.
     *
     * @param description A string that determines the type of edit to be performed.
     * @param taskIndex The index of the task in the task list that is to be edited.
     */
    public EditCommand(String description, int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
        this.description = description;
    }

    /**
     * Executes the command to edit a task's properties based on the given description
     * and task index.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface interactions.
     * @param storage The system's storage utility.
     * @return A string that conveys the result of the execution.
     * @throws DukeException If there is an error during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (taskIndex <= 0 || taskIndex > tasks.size()) {
                throw new DukeException(ui.invalidIndexError(taskIndex));
            }

            Task currTask = tasks.getList().get(taskIndex - 1);
            if (Objects.equals(description, "mark")) {
                currTask.setCompleted();
                return ui.showMarkedTask(taskIndex, currTask);
            } else if (Objects.equals(description, "unmark")) {
                currTask.setNotCompleted();
                return ui.showUnmarkedTask(taskIndex, currTask);
            } else {
                throw new DukeException("Invalid edit description provided.");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
