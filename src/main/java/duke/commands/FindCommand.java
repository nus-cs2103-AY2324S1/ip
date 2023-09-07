package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The FindCommand search the TaskList with a given query
 * and display the task found when it is executed.
 */
public class FindCommand extends Command {
    private final String query;
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws IOException {
        ArrayList<Task> results = taskList.findTasks(query);
        return message.showSearchResults(results);
    }
}
