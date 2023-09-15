package duke.command;

import java.util.Map;

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
        default:
            break;
        }
    }

    @Override
    protected void checkIfParametersSpecified() throws DukeException {
        if (sortBy == null) {
            throw new DukeException("Please enter a sort type.\n"
                    + "Valid sort types are: name, type, completion");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.sort(sortBy);

        StringBuilder response = new StringBuilder("Tasks sorted by ");
        response.append(sortBy.toString());
        return response.toString();
    }
}
