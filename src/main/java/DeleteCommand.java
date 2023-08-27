public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDeleteTask(tasks.getTask(this.index), tasks.getCountTasks() - 1);
        tasks.removeTask(this.index);
    }
}
