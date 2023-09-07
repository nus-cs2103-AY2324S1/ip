package duke;

/**
 * Marks a task in task list as not done
 */
public class UnmarkCommand extends Command{

    /** Index of the task in task list */
    private final int INDEX;

    public UnmarkCommand(int i) {
        INDEX = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(INDEX);
        return ui.showUnmarked(tasks.get(INDEX));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
