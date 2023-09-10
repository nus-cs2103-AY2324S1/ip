package potato.command;

import java.io.IOException;

import potato.*;

public abstract class Command {
    protected boolean isExit;
    protected String input;

    public boolean isExit() {
        return isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
