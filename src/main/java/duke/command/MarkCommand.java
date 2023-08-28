package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        super(false);
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.mark(index);
        storage.rewrite(tasks);
        ui.showMarkMessage();
    }
}
