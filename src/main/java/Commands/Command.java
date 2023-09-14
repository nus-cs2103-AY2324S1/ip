package Commands;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;

/** The interface that allows for the Command pattern which is based on requests. */
public interface Command {
    /**
     * Performs an action tied to the command.
     *
     * @param tasks TaskList instance that the Duke uses.
     * @param ui ui instance that is used in the current Duke application instance.
     * @param storage storage instance that is used in the current Duke application instance.
     */
    String execute(TaskList tasks, Ui ui, Storage storage);
}
