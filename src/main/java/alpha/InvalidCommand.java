package alpha;

public class InvalidCommand extends Command {

    private String errorMessage;

    public InvalidCommand(TaskList taskList, FileHandler fileHandler, UI ui, String errorMessage) {
        super(taskList, fileHandler, ui);
        isExit = false;
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute() {
        return errorMessage;
    }
}
