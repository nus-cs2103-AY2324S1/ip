package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.Task;
import rua.task.TaskList;


public class AddCommand implements Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     *
     * @return The exit status after this execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddCommand)) {
            return false;
        }

        AddCommand c = (AddCommand) o;
        return c.task.equals(this.task);
    }


    /**
     * Adds the task into a TaskList and returns the updated TaskList after the execution.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The updated TaskList after adding the Task.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        final String addTaskMessage = " Got it. I've added this task:\n";
        TaskList newTasks = tasks.add(task);
        final String addingTaskInfo = "    " + task + "\n";
        final String totalNumberTaskMessage = "Now you have " + tasks.getTasks().size()
                + " tasks in the list.\n";
        ui.showMessage(addTaskMessage);
        ui.showMessage(addingTaskInfo);
        ui.showMessage(totalNumberTaskMessage);
        storage.save(tasks);
        return newTasks;
    }
}
