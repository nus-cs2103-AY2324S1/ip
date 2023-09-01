package command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.util.ArrayList;

public class ListCommand extends Command{
    public static final String COMMAND_LIST = "list";

    public ListCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.printContents();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
