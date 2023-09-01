package duke;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public static final String  COMMAND_EXIT = "bye";
    public ExitCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printGoodbyeMessage();
        ui.closeUi();
    }
}
