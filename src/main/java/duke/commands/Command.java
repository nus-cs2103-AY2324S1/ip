package duke.commands;

import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * This class provides a framework for commands that can be issued to the application.
 * Individual commands should extend this class and provide specific implementations
 * for the execute method.
 */
public abstract class Command {
    /**
     * Executes the command with respect to the provided task list, UI, and storage.
     * This method needs to be implemented by all concrete subclasses of Command.
     *
     * @param tasks The task list on which the command operates.
     * @param ui The UI with which user interactions can be managed.
     * @param storage The storage mechanism used to load or save tasks.
     * @throws Exception If there's any error during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
