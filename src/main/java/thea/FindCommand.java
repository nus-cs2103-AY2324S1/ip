package thea;

/**
 * Represents an executable user command which finds tasks
 * in the task list according to a keyword.
 * This class has data on the keyword.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword the keyword to be found in tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Finds the tasks containing the keyword from the taskList.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.relevantTasksFound(tasks.find(keyword));
    }
}
