package thea;

/**
 * Represents an executable command that prints the current task list.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class PrintListCommand extends Command {

    /**
     * Constructs a new MarkCommand object.
     */
    public PrintListCommand() {
        super(false);
    }

    /**
     * Prints the current task list.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
