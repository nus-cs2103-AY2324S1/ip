/**
 * The abstract Command class represents a generic command in the application.
 * Specific command types (e.g., ToDoCommand, DeadlineCommand) extend this class.
 * When executed, a command performs a specific action based on its type.
 */
public abstract class Command {

    /**
     * Constructs a generic Command object.
     */
    public Command() {
    }

    /**
     * Executes the command, which performs a specific action based on its type.
     * This method should be overridden by subclasses to implement specific command functionality.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    public abstract void execute(TaskList tasksList, Ui ui, Storage storage);
}