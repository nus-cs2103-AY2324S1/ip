package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Nice! I've marked this task as done:\n\t%s\n", tasks.markTask(index)));
        storage.save(tasks);
    }
}
