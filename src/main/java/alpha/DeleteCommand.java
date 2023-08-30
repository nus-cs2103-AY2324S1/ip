package alpha;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(TaskList taskList, FileHandler fileHandler, UI ui, int index) {
        super(taskList, fileHandler, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute() {
        ui.delete(taskList.delete(index), taskList.size());
        fileHandler.delete(index);
    }
}
