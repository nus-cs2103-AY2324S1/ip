package alpha;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(TaskList taskList, FileHandler fh, UI ui, int index) {
        super(taskList, fh, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public String execute() {
        Task unmarkedTask = taskList.unmark(index);
        fileHandler.checkOrUncheck(index, false);
        return ui.unmark(unmarkedTask);

    }
}
