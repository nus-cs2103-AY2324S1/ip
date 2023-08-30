package commands;

import services.Storage;
import services.TaskList;
import services.UI;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.shutDown();
    }
}
