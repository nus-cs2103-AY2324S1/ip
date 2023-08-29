public class MarkCommand extends Command {
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
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("OOPS!!! The task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showDone(task);
        storage.save(tasks);
    }
}
