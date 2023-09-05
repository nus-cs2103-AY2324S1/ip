package thea;

/**
 * Represents an executable command that marks a task as done.
 * This class has data on the index of task to be marked.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class MarkCommand extends Command {
    int index;

    /**
     * Constructs a new MarkCommand object.
     *
     * @param index the index of task to be marked as done.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks the task specified by index as done.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(this.index);
            ui.taskMark(tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
    }
}
