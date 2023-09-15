package potato.command;

import java.io.IOException;

import potato.*;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.delete(input, storage);
    }
}
