package commands;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/** The interface that allows for the Command pattern which is based on requests. */
public interface Command {
    /**
     * Performs an action tied to the command.
     *
     * @param tasks TaskList instance that the duke uses.
     * @param ui ui instance that is used in the current duke application instance.
     * @param storage storage instance that is used in the current duke application instance.
     */
    String execute(TaskList tasks, Ui ui, Storage storage);
}
