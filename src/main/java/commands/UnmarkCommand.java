package commands;

import errors.DotException;
import tasks.TaskList;

import java.io.File;

public class UnmarkCommand extends Command {
    private final int position;
    private final TaskList dotTaskList;
    private final String dataFilePathname;
    public UnmarkCommand(int position, TaskList dotTaskList,
                       String dataFilePathname) {
        this.position = position;
        this.dotTaskList = dotTaskList;
        this.dataFilePathname = dataFilePathname;
    }

    @Override
    public void execute() throws DotException {
        dotTaskList.markTask(position - 1, false);
        dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
    }

}
