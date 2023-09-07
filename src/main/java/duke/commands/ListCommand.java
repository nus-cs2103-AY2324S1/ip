package duke.commands;

import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            String emptyListMessage = "There are no tasks in your list.";
            ui.appendResponse(emptyListMessage);
            return;
        }

        String message = "Here are the tasks in your list:\n";
        ui.appendResponse(message + tasks);
    }
}
