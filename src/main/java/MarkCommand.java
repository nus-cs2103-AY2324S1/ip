public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.mark(this.taskIndex);
        ui.showMark(tasks.taskList.get(this.taskIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
