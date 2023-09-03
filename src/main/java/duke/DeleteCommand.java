package duke;

/**
 * Deletes a task from  the task list
 */
public class DeleteCommand extends Command{

    /** Index of the task in task list */
    private final int index;
    public DeleteCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.get(index);
        tasks.delete(index);
        ui.showDeleted(t, tasks.total());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
