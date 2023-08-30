package alpha;
public class ListCommand extends Command{

    public ListCommand(TaskList taskList, FileHandler fh, UI ui) {
        super(taskList, fh, ui);
        super.isExit = false;
    }

    public void execute() {
        this.ui.list(taskList.size(), taskList);
    }

}
