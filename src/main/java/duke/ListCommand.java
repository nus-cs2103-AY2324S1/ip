package duke;

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
}
