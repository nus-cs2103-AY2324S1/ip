package Command;

import Exception.*;
import Helper.*;
import Task.*;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.unmark(this.getIndex());
        ui.print(message);
    }
}