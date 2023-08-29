package src.alpha;
public class ExitCommand extends Command{

    public ExitCommand(TaskList taskList, FileHandler fh, UI ui) {
        super(taskList, fh, ui);
        super.isExit = true;
    }

    public void execute() {
        return;
    }
}
