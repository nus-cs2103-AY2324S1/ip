package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;
import java.io.IOException;

public class ListCommand extends Command {
    public ListCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showList(tasks.getTasks());
    }
}
