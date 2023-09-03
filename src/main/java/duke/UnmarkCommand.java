package duke;

/**
 * Marks a task in task list as not done
 */
public class UnmarkCommand extends Command{

    /** Index of the task in task list */
    private final int index;
    public UnmarkCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(index);
        ui.showUnmarked(tasks.get(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
