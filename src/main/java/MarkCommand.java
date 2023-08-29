public class MarkCommand extends Command{

    private int index;

    public MarkCommand(TaskList taskList, FileHandler fh, UI ui, int index) {
        super(taskList, fh, ui);
        super.isExit = false;
        this.index = index;
    }

    public void execute() {
            ui.mark(taskList.mark(index));
            this.fileHandler.checkOrUncheck(index, true);
    }
}
