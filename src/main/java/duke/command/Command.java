package duke.command;

/** Abstraction to carry out a command input by user. */
public abstract class Command {

    /** Perform a specific command given a specific input by user */
    public abstract String execute();
}
