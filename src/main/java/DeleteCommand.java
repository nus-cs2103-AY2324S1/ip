public class DeleteCommand extends Command {
    String pos;
    public DeleteCommand(String pos) {
        this.pos = pos;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = list.deleteTask(this.pos);
        ui.showDelete(deletedTask, list.getList());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
