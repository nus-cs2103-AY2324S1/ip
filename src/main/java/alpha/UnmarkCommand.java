package alpha;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(TaskList taskList, FileHandler fh, UI ui, int index) {
        super(taskList, fh, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute() {
        Task unmarkedTask = taskList.unmark(index);
        ui.unmark(unmarkedTask);
        fileHandler.checkOrUncheck(index, false);
    }
}
