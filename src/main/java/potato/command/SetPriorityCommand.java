package potato.command;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

import java.io.IOException;

public class SetPriorityCommand extends Command {
    public SetPriorityCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.setPriority(input, storage);
    }
}
