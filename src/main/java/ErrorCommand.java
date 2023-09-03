public class ErrorCommand extends Command {
    String errorMessage;
    ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.showError(this.errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
