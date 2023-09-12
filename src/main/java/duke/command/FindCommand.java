package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The FindCommand class represents a command to find tasks based on a keyword.
 * It is a subclass of the Command class.
 */
public class FindCommand extends Command {

    public FindCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws DukeException {
        TaskList resultList = new TaskList();
        String keyword = commandDetails.get(0);

        for (Task task : tasks) {
            if (task.getDescription().indexOf(keyword) != -1) {
                resultList.add(task);
            }
        }

        return response.printFind(resultList);
    }
}
