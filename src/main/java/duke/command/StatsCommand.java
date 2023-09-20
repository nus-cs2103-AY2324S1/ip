package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.Ui;

/**
 * The StatsCommand class represents a command that provides statistics about tasks.
 * It calculates the percentage of completed tasks and counts tasks of different types.
 *
 * @author selwyn
 */
public class StatsCommand extends Command {
    /**
     * Executes the StatsCommand to calculate and display statistics about tasks.
     *
     * @param taskList The task list containing all tasks.
     * @param storage  The storage object used to read/write task data.
     * @return A string containing the statistics to be displayed to the user.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        ArrayList<Task> todoTasks = taskList.listSpecificTaskType(TaskType.TODO);
        ArrayList<Task> deadlineTasks = taskList.listSpecificTaskType(TaskType.DEADLINE);
        ArrayList<Task> eventTasks = taskList.listSpecificTaskType(TaskType.EVENT);

        double percentDone = taskList.statePercentOfTasksDone();

        return Ui.printStatistics(todoTasks.size(), deadlineTasks.size(), eventTasks.size(), percentDone);
    }
}
