package duke.command;

import java.util.Map;
import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!super.getParameterMap().containsKey("default")) {
            throw new DukeException("No key to search for specified. Please specify a key.");
        }

        String keyword = super.getParameterMap().get("default");
        Stream<String> taskDetails = tasks.getTasks().map(task -> task.toString())
                .filter(task -> task.contains(keyword));
        Ui.respond(taskDetails);
    }
}
