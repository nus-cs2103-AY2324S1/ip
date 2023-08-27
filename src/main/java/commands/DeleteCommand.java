package commands;

import errors.DotException;
import tasks.TaskList;

import java.io.File;

public class DeleteCommand extends Command {

    private final int position;
    private final TaskList dotTaskList;
    private final String dataFilePathname;
    public DeleteCommand(int position, TaskList dotTaskList,
                         String dataFilePathname) {
        this.position = position;
        this.dotTaskList = dotTaskList;
        this.dataFilePathname = dataFilePathname;
    }

    @Override
    public void execute() throws DotException {
        dotTaskList.deleteTask(position - 1);
        dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
    };

}
