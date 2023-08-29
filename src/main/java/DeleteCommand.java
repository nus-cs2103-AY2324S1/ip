public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            ui.showDeleteMessage(tasks.getSize(), task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
