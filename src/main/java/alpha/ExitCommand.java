package alpha;

public class ExitCommand extends Command {

    public ExitCommand(TaskList taskList, FileHandler fileHandler, UI ui) {
        super(taskList, fileHandler, ui);
        isExit = true;
    }

    @Override
    public String execute() {
        // No need to do anything here for an ExitCommand
        return "";
    }
}
