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

    /**
     * Constructor for FindCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public FindCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (!super.getParameterMap().containsKey("default")) {
            throw new DukeException("No key to search for specified. Please specify a key.");
        }

        AtomicInteger count = new AtomicInteger(1);

        String keyword = super.getParameterMap().get("default");
        Stream<String> taskDetails = tasks.getTasks().map(task -> task.toString())
                .filter(task -> task.contains(keyword)).map(task -> String.format("%d. $d\n", count.getAndIncrement(), tasks));

        if (taskDetails.count() == 0) {
            return "There are no matching tasks found.";
        }
        
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        taskDetails.forEach(task -> response.append(task));

        return response.toString();
    }
}
