package puke.command;

import puke.managers.TaskList;

/**
 * A Class that represents an action to be taken by the chatbot.
 * All Commands can be executed to print its corresponding message and carry out corresponding actions.
 */
public abstract class Command {
    protected static final String ERROR_MESSAGE = "Unfortunately, the circumstances preceding this has necessitated "
            + "that I issue an apology for the input that I have received is unrecognised.";
    protected boolean isValid;
    private final boolean exit;


    Command(boolean exit, boolean valid) {
        this.exit = exit;
        this.isValid = valid;
    }
    public abstract String execute(TaskList tl);

    public boolean isExit() {
        return this.exit;
    }
}
