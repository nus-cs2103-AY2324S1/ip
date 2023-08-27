package commands;

import errors.DotException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

import java.io.File;

public class DeadlineCommand extends Command {
    private final String description;
    /** String as this is still the command layer */
    private final String deadline;
    private final TaskList dotTaskList;
    private final String dataFilePathname;

    public DeadlineCommand(String description, String deadline,
                           TaskList dotTaskList, String dataFilePathname) {
        this.description = description;
        this.deadline = deadline;
        this.dotTaskList = dotTaskList;
        this.dataFilePathname = dataFilePathname;
    }

    @Override
    public void execute() throws DotException {
        Task newDeadlineTask = new Deadline(this.description, this.deadline);
        dotTaskList.addTask(newDeadlineTask);
        dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
    };

}
