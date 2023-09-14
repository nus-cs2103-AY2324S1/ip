package atlas.commands;

import java.io.IOException;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;

/**
 * Command to unmark one or more Tasks in the TaskList
 */
public class UnmarkTaskCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "OK, I've marked these tasks as not done yet:";
    private final int[] indices;

    /**
     * Constructs a MarkTaskCommand
     * @param indices Indices of task to mark
     */
    public UnmarkTaskCommand(int... indices) {
        this.indices = indices;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            List<Task> tasksNotDone = taskList.unmarkTasks(indices);
            storage.save(taskList);
            return generateListOutput(tasksNotDone, OUTPUT_HEADER_MESSAGE);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
