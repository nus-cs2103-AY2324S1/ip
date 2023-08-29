package Command;

import Exception.*;
import Helper.*;
import Task.*;;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.mark(this.getIndex());
        ui.print(message);
    }
}