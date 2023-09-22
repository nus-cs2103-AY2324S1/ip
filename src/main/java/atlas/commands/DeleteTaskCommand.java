package atlas.commands;

import java.io.IOException;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to delete a Task from the TaskList
 */
public class DeleteTaskCommand extends Command {
    private final int idx;

    /**
     * Constructs a DeleteTaskCommand
     * @param idx Index of task to mark
     */
    public DeleteTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        assert storage != null;
        try {
            Task deletedTask = taskList.deleteTask(idx);
            storage.save(taskList);
            return generateExecutionOutput(deletedTask, taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates message indicating that the task has been deleted
     * @param task Task to be deleted
     * @param taskList TaskList to delete the task from
     * @return Message indicating that the task has been deleted
     */
    private String generateExecutionOutput(Task task, TaskList taskList) {
        String newTaskCountMessage = taskList.getCountString();
        return String.format("So let it be struck off:\n\n"
                + "%s\n\n"
                + "%s", task, newTaskCountMessage);
    }
}
