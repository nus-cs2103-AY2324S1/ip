public class Command {
    private String command;
    private boolean isExit;

    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Command not implemented");
    }


}
