public abstract class Command {
    private String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
