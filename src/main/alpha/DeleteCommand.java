package src.alpha;
public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(TaskList taskList, FileHandler fh, UI ui, int index) {
        super(taskList, fh, ui);
        super.isExit = false;
        this.index = index;
    }

    public void execute() {
        ui.delete(taskList.delete(index), taskList.size());
        fileHandler.delete(index);
    }
}
