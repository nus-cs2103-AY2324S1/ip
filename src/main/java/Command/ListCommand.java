package Command;

import Helper.*;
import Task.*;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.print(list.print());
    }
}
