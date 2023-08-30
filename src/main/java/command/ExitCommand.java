package command;

import helper.Storage;
import helper.Ui;
import task.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand(int index) {
        super(index);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.print("Bye. Hope to see you again soon lol!");
    }
}
