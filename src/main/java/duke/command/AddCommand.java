package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;

import java.io.IOException;

public class AddCommand extends Command {
    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.append(task);
        ui.showAddSuccess(task.toString(), tasks.getTasks().size());
    }
}
