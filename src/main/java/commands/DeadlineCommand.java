package commands;

import errors.DotException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

/**
 * Command to add a Deadline task to dotTaskList.
 */
public class DeadlineCommand extends Command {
    private final String description;

    /**
     * Deadline as String as this is still the command layer.
     */
    private final String deadline;
    private final TaskList dotTaskList;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description This is the description for the deadline task.
     * @param deadline    This is the description of the deadline as a String.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public DeadlineCommand(String description, String deadline, TaskList dotTaskList) {
        this.description = description;
        this.deadline = deadline;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Creates and inserts the Deadline into dotTaskList.
     *
     * @throws DotException On detected error.
     */
    @Override
    public void execute() throws DotException {
        Task newDeadlineTask = new Deadline(this.description, this.deadline);
        dotTaskList.addTask(newDeadlineTask);
        dotTaskList.saveTaskListToStorage();
    }

}
