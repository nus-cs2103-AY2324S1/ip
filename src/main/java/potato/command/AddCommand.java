package potato.command;

import potato.*;
import java.io.IOException;

public class AddCommand extends Command{
    public AddCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(input, storage);
    }
}
