package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;

/**
 * The FindCommand class represents a command to search for tasks within the TaskList
 * based on a specified query and display the found tasks when executed.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructs a new FindCommand with the provided query.
     *
     * @param query The search query used to find matching tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws IOException {
        ArrayList<Task> results = taskList.findTasks(query);
        return message.showSearchResults(results);
    }
}
