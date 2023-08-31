package harvard;
public class UnmarkCommand extends Command{
    private int index;
    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Constructs a UnmarkCommand object.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("OOPS!!! The task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        ui.showUndone(task);
        storage.save(tasks);
    }
}
