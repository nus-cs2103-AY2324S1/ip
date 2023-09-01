package duke;

import java.util.ArrayList;

public class FinaCommand extends Command{
    public static final String COMMAND_FIND = "find";

    public FinaCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> tasksContainingKeyword = tasks.getTasksContainingKeyword(params.get(1));
        ui.printTasksMatching(tasksContainingKeyword);
    }
}
