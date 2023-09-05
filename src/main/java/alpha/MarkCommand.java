package alpha;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(TaskList taskList, FileHandler fileHandler, UI ui, int index) {
        super(taskList, fileHandler, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public String execute() {
        fileHandler.checkOrUncheck(index, true);
        return ui.mark(taskList.mark(index));
    }
}
