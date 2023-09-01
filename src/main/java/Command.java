import java.io.IOException;

public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)  {

    }

    public boolean isExit() {
        return this.isExit;
    }
}