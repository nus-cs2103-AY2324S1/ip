package potato.command;

import potato.*;
import java.io.IOException;

public class FindCommand extends Command{
    public FindCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.find(input);
    }
}