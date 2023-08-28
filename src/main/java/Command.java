public abstract class Command {
    private String fullCommand;
    private boolean isExit;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        this.isExit = false;
    }

    public Command() {
        this.fullCommand = "";
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit() {
        this.isExit = true;
    }

    public String getCommand() {
        return this.fullCommand;
    }

    public void setCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}
