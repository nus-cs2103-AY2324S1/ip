package thea;

/**
 * Represents an executable command that unmarks a task as done.
 * This class has data on the index of task to be unmarked.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class UnmarkCommand extends Command {
    private final int index;

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
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        String output;
        try {
            thea.tasks.unmark(this.index);
            output = thea.ui.taskUnmarked(thea.tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            output = thea.ui.showError(e.getMessage());
        }
        thea.storage.saveTaskList(thea.tasks);
        return output;
    }
}
