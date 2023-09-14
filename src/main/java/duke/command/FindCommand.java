package duke.command;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.TaskList;

/**
 * Command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public FindCommand(Map<String, String> parameterMap) throws DukeException {
        super(parameterMap);

        this.loadParameters();
        this.checkIfParametersSpecified();
    }

    @Override
    public void loadParameters() throws DukeException {
        keyword = parameterMap.get("default");
    }

    @Override
    public void checkIfParametersSpecified() throws DukeException {
        if (keyword == null || keyword.isEmpty()) {
            throw new DukeException("No key to search for specified. Please specify a key.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        AtomicInteger count = new AtomicInteger(1);

        Stream<String> taskDetails = tasks.getTasks().map(task -> task.toString())
                .filter(task -> task.contains(keyword))
                .map(task -> String.format("%d. $d\n", count.getAndIncrement(), tasks));

        if (taskDetails.count() == 0) {
            return "There are no matching tasks found.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        taskDetails.forEach(task -> response.append(task));

        return response.toString();
    }
}
