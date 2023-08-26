package main.java.actions;

/**
 * Action class that corresponds to an error action.
 *
 * This class may be deprecated in future use for favour of proper exception handling.
 */
public class JukeExceptionAction extends JukeAction {
    /** Error description. */
    private final Exception err;

    /**
     * Constructor to create an Error Action.
     * @param err Error description
     */
    public JukeExceptionAction(Exception err) {
        this.err = err;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public void complete() {
        System.out.print("Error: " + err);
    }
}
