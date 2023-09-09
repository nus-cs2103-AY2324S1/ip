package thea;

/**
 * Represents an executable user command which deletes an existing task in the taskList.
 * This class has data on the index of task to be deleted.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param index the index of task to be deleted.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Delete the task specified by index from the taskList.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output;
        try {
            try {
                output = ui.taskDeleted(tasks.get(this.index), tasks);
            } catch (java.lang.IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
            }
            tasks.delete(this.index);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
        return output;
    }
}
