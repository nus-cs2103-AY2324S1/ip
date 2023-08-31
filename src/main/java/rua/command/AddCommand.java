package rua.command;

import rua.common.Ui;
import rua.common.Storage;
import rua.task.TaskList;
import rua.task.Task;

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
        ui.showMessage(" Got it. I've added this task:\n");
        TaskList newTasks = tasks.add(task);
        ui.showMessage("    " + task + "\n");
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in the list.\n");
        storage.save(tasks);
        return newTasks;
    }
}
