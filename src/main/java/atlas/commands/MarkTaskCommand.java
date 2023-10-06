package atlas.commands;

import java.io.IOException;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;

/**
 * Command to mark one or more Tasks in the TaskList
 */
public class MarkTaskCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "You might be strong enough to bear my burdens "
            + "after all, mortal. These labours are done:";
    private final Integer[] indices;

    /**
     * Constructs a MarkTaskCommand
     * @param indices Indices of tasks to mark
     */
    public MarkTaskCommand(Integer... indices) {
        this.indices = indices;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        assert storage != null;
        try {
            List<Task> tasksDone = taskList.markTasks(indices);
            storage.save(taskList);
            return generateListOutput(tasksDone, OUTPUT_HEADER_MESSAGE);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
