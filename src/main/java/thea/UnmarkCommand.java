package thea;

/**
 * Represents an executable command that unmarks a task as done.
 * This class has data on the index of task to be unmarked.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * Constructs a new UnmarkCommand object.
     *
     * @param index the index of task to be unmarked as done.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Unmarks the task specified by index as done.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(this.index);
            ui.taskUnmarked(tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
    }
}
