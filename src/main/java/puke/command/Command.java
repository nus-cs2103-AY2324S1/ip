package puke.command;

import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A Class that represents an action to be taken by the chatbot.
 * All Commands can be executed to print its corresponding message and carry out corresponding actions.
 */
public abstract class Command {
    protected boolean isValid;
    private final boolean exit;

    Command(boolean exit, boolean valid) {
        this.exit = exit;
        this.isValid = valid;
    }
    public abstract void execute(TaskList tl, Ui ui);

    public boolean isExit() {
        return this.exit;
    }
}
