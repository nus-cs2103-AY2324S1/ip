package alpha;

/**
 * Class representation of an abstract Command. All other Commands inherit from this class.
 * Commands modify the task list in some way.
 *
 * @author Wong Joon Hung
 */
public abstract class Command {

    protected TaskList taskList;
    protected FileHandler fileHandler;
    protected UI ui;
    protected boolean isExit;

    /**
     * Constructor for the Command class.
     *
     * @param taskList    The taskList that the command will modify.
     * @param fileHandler The fileHandler that the command will modify.
     * @param ui          The user interface the command interacts with.
     */
    public Command(TaskList taskList, FileHandler fileHandler, UI ui) {
        this.taskList = taskList;
        this.fileHandler = fileHandler;
        this.ui = ui;
    }

    public abstract String execute();

    public boolean isExit() {
        return isExit;
    }
}
