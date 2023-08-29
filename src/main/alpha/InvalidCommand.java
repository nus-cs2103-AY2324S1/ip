package src.alpha;
public class InvalidCommand extends Command{

    public InvalidCommand(TaskList taskList, FileHandler fh, UI ui) {
        super(taskList, fh, ui);
        super.isExit = false;
    }

    public void execute() {
    }
}
