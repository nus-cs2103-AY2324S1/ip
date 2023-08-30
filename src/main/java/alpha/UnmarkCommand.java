package alpha;
public class UnmarkCommand extends Command{

    private int index;

    public UnmarkCommand(TaskList taskList, FileHandler fh, UI ui, int index) {
        super(taskList, fh, ui);
        super.isExit = false;
        this.index = index;
    }

    public void execute() {
        ui.mark(taskList.unmark(index));
        this.fileHandler.checkOrUncheck(index, false);
    }

}
