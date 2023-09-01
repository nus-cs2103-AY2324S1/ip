package rocket;

public class Command {

    private boolean isExit;
    public Command (boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    /**
     * Tells Rocket if the command is an exit command
     * @return a boolean: true if the command is an exit commnad, false if not
     */
    public boolean isExit() {
        return isExit;
    }
}
