package potato.command;

import potato.*;

public class ExitCommand extends Command{
    public ExitCommand(String input) {
        super.isExit = true;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
