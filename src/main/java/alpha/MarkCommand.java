package alpha;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(TaskList taskList, FileHandler fileHandler, UI ui, int index) {
        super(taskList, fileHandler, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute() {
        ui.mark(taskList.mark(index));
        fileHandler.checkOrUncheck(index, true);
    }
}
