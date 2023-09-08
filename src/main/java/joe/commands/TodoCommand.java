package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.tasks.TodoTask;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class TodoCommand extends Command {
    private final String taskDetails;

    /**
     * Constructs a TodoCommand with task details.
     *
     * @param taskDetails The description of the to-do task.
     */
    public TodoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Executes the command to add a to-do task to the task list.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TodoTask newTask = new TodoTask(taskDetails);
        tasks.add(newTask);

        storage.saveToFile(tasks);

        return (
                String.format(
                        "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.",
                        newTask, tasks.size()));
    }
}
