package juke.commands;

/**
 * Action class that corresponds to an error action.
 */
public class JukeExceptionCommand extends JukeCommand {
    /** Error description. */
    private final Exception err;

    /**
     * Constructor to create a {@code JukeExceptionCommand}.
     *
     * @param err Error description
     */
    public JukeExceptionCommand(Exception err) {
        this.err = err;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void complete() {
        System.out.print("Error: " + err);
    }
}
