package duke.command;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.TaskList;
import duke.task.TaskList.SortBy;

/**
 * Command to sort the task list.
 */
public class SortCommand extends Command {
    private SortBy sortBy;

    /**
     * Constructor for MarkCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public SortCommand(Map<String, String> parameterMap) throws DukeException {
        super(parameterMap);

        this.loadParameters();
        this.checkIfParametersSpecified();
        this.checkIfParametersValid();
    }

    @Override
    protected void loadParameters() throws DukeException {
        String sortByString = parameterMap.get("default");

        if (sortByString == null) {
            return;
        }

        switch(sortByString) {
        case "name":
            sortBy = SortBy.NAME;
            break;
        case "type":
            sortBy = SortBy.TASK;
            break;
        case "completion":
            sortBy = SortBy.COMPLETION;
            break;
        case "date":
            sortBy = SortBy.DATE;
            break;
        default:
            break;
        }
    }

    @Override
    protected void checkIfParametersSpecified() throws DukeException {
        if (sortBy == null) {
            throw new DukeException("Please enter a valid sort type.\n"
                    + "Valid sort types are: name, type, completion, date.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.sort(sortBy);

        tasks.storeTasks(storage);

        AtomicInteger count = new AtomicInteger(1);
        StringBuilder response = new StringBuilder();
        response.append(String.format("Tasks sorted by %s:\n", sortBy.toString()));
        tasks.getTasks().map(task -> String.format("%d. %s\n", count.getAndIncrement(), task.toString()))
                .forEach(task -> response.append(task));

        return response.toString();
    }
}
