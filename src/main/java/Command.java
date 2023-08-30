import java.io.IOException;

public abstract class Command {
    private boolean exit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, HelpBuddyException;

    public boolean isExit() {
        return this.exit;
    }

    public void toggleExit() {
        this.exit = !this.exit;
    }
}
