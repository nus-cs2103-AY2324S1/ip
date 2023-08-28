package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    String text;

    public FindCommand(String text) {
        super(false);
        this.text = text;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> found = tasks.find(text);
        ui.showFoundTask(found);
    }
}
