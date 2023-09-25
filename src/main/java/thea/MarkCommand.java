package thea;

/**
 * Represents an executable command that marks a task as done.
 * This class has data on the index of task to be marked.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class MarkCommand extends Command {
    private final int index;

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
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        String output = "";
        try {
            thea.tasks.mark(this.index);
            output += thea.ui.taskMarked(thea.tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            output += thea.ui.showError(e.getMessage());
        }
        thea.storage.saveTaskList(thea.tasks);
        return output;
    }
}
