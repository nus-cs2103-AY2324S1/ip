package duke;

/**
 * ExitTask class that is a command of a user
 */
public class ExitTask extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeScanner();
        return ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || this == null) {
            return false;
        } else {
            return obj instanceof ExitTask;
        }
    }
}
