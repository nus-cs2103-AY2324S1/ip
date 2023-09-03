package duke;

/**
 * Marks a task in task lis as done
 */
public class MarkCommand extends Command{

    /** Index of the task in task list */
    private final int index;
    public MarkCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(index);
        ui.showMarked(tasks.get(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
