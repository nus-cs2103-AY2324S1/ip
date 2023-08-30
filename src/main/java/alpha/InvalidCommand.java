package alpha;

public class InvalidCommand extends Command {

    public InvalidCommand(TaskList taskList, FileHandler fileHandler, UI ui) {
        super(taskList, fileHandler, ui);
        isExit = false;
    }

    @Override
    public void execute() {
        // Nothing to execute for an invalid command
    }
}
