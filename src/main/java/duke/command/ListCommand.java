package duke.command;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import duke.core.Storage;
import duke.task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public ListCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "There are no tasks to list.";
        }

        AtomicInteger count = new AtomicInteger(1);
        Stream<String> taskDetails = tasks.getTasks().map(task -> String.format("%d. %s\n", count.getAndIncrement(), task.toString()));

        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        taskDetails.forEach(task -> response.append(task));

        return response.toString();
    }
}
