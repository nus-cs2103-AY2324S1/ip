package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.print((i + 1) + "." + tasks.getTaskString(i + 1));
        }
    }

    @Override
    public String getCommandType() {
        return "List";
    }
}
