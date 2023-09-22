package atlas.commands;

import java.io.IOException;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to add task to task list
 */
public class AddTaskCommand extends Command {
    protected final Task task;

    /**
     * Constructs an AddTaskCommand
     * @param t Task to be added
     */
    public AddTaskCommand(Task t) {
        task = t;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        assert storage != null;
        try {
            taskList.addTask(task);
            storage.save(taskList);
            return generateExecutionOutput(taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates message indicating the task that has been added.
     * @param taskList TaskList that the task has been added to
     * @return Message indicating the task has been added
     */
    private String generateExecutionOutput(TaskList taskList) {
        String newTaskCountMessage = taskList.getCountString();
        return String.format("Got it. I've added this task:\n\n"
                + "\t%s\n\n"
                + "%s", task, newTaskCountMessage);
    }
}
