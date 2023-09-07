public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.remove(taskIndex - 1);
            storage.save(tasks.getList());
            ui.showDeletedTask(taskIndex, deletedTask);
        } catch(Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
