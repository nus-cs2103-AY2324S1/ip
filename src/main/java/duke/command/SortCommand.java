package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to sort task-list.
 * A <code>SortCommand</code> object corresponds to an executable command
 * to sort the task-list either by: task descriptions, deadlines, and categories,
 * where Todo > Deadline > Event.
 * @see duke.task.Todo
 * @see duke.task.Deadline
 * @see duke.task.Event
 */
public class SortCommand extends Command {
    private final String commandBody;
    private final boolean isDefaultSort;

    /**
     * Constructs a <code>sort</code> command.
     * The default sorting is used.
     */
    public SortCommand() {
        super(false);
        this.commandBody = "";
        this.isDefaultSort = true;
    }

    /**
     * Constructs a <code>sort</code> command.
     *
     * @param commandBody The command body.
     */
    public SortCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
        this.isDefaultSort = false;
    }

    /**
     * Executes the sort command to sort task-list in specific ways.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage.
     * @return the response to the user.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        if (isDefaultSort) {
            taskList.sortTaskList(Task::compareTo);
        } else {
            String sortType = commandBody.toLowerCase();
            switch (sortType) {
            case "name":
                taskList.sortTaskList(Task::compareTo);
                break;

            case "deadline":
                taskList.sortTaskList(Task::compareDeadline);
                break;

            case "type":
                taskList.sortTaskList(Task::compareType);
                break;

            default:
                throw new DukeException(String.format("OOPS!!! The sort type \"%s\" is invalid.",
                        commandBody));
            }
        }

        storage.saveTasks(taskList.saveTaskList());
        return ui.showSort(isDefaultSort, commandBody);
    }
}
