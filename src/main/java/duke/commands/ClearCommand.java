package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command that clear all things in storage.
 */
public class ClearCommand extends Command {
    public ClearCommand() {
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The ui.
     * @param storage The storage.
     * @throws StorageException       If there is an error in accessing the storage file.
     * @throws CommandDetailException If there is an error in the details of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.clearTasks();
        ui.showClear();
        storage.save(tasks);
    }

    /**
     * Executes the command and returns the result.
     *
     * @param tasks   The task list.
     * @param ui      The ui.
     * @param storage The storage.
     * @return The result of the execution.
     * @throws StorageException       If there is an error in accessing the storage file.
     * @throws CommandDetailException If there is an error in the details of the command.
     */
    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.clearTasks();
        String output = ui.showClearGui();
        storage.save(tasks);
        return output;
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
