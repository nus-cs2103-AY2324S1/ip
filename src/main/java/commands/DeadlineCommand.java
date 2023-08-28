package commands;

import errors.DotException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

public class DeadlineCommand extends Command {
    private final String description;
    /**
     * String as this is still the command layer
     */
    private final String deadline;
    private final TaskList dotTaskList;

    public DeadlineCommand(String description, String deadline, TaskList dotTaskList) {
        this.description = description;
        this.deadline = deadline;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        Task newDeadlineTask = new Deadline(this.description, this.deadline);
        dotTaskList.addTask(newDeadlineTask);
        dotTaskList.saveTaskListToStorage();
    }

}
