package duke;

/**
 * Deletes a task from  the task list
 */
public class DeleteCommand extends Command{

    /** Index of the task in task list */
    private final int INDEX;

    public DeleteCommand(int i) {
        INDEX = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.get(INDEX);
        tasks.delete(INDEX);
        return ui.showDeleted(t, tasks.total());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
