package alpha;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, FileHandler fileHandler, UI ui) {
        super(taskList, fileHandler, ui);
        isExit = false;
    }

    @Override
    public void execute() {
        ui.list(taskList);
    }
}
