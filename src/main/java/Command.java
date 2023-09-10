public abstract class Command {
    protected String commandDescription;

    public Command(String description) {
        this.commandDescription = description;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException;

    public boolean isExit() {
        return false;
    }
}
