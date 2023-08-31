package duke.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.task.Event;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ArrayList<Task> results = taskList.findTasks(query);
        ui.showSearchResults(results);
    }
}
