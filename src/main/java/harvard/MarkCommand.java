package harvard;
/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Constructs a MarkCommand object.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("OOPS!!! The task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        return ui.showDone(task);
    }
}
