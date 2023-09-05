package alpha;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(TaskList taskList, FileHandler fileHandler, UI ui, int index) {
        super(taskList, fileHandler, ui);
        isExit = false;
        this.index = index;
    }

    @Override
    public String execute() {
        fileHandler.delete(index);
        return ui.delete(taskList.delete(index), taskList.size());
    }
}
