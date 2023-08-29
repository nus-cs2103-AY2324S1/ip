package Command;

import Exception.*;
import Helper.*;
import Task.*;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.delete(this.getIndex());
        ui.print(message);
    }
}
