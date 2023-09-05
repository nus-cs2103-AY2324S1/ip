package thea;

/**
 * Represents an executable user command which exits the program.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     * IsExit is true as this is an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Print the exiting message and notify Main
     * to exit the program.
     * Saves the taskList before exiting.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        storage.saveTaskList(tasks);
    }
}
