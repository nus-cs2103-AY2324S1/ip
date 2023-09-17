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
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        String output;
        try {
            try {
                output = thea.ui.taskDeleted(thea.tasks.get(this.index), thea.tasks);
            } catch (java.lang.IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
            }
            thea.tasks.delete(this.index);
        } catch (IndexOutOfBoundsException e) {
            return thea.ui.showError(e.getMessage());
        }
        thea.storage.saveTaskList(thea.tasks);
        return output;
    }
}
