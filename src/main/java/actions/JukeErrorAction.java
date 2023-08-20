package main.java.actions;

import main.java.JukeASCIIColours;

import java.util.Optional;

/**
 * Action class that corresponds to an error action.
 *
 * This class may be deprecated in future use for favour of proper exception handling.
 */
public class JukeErrorAction extends JukeAction{
    /** Error description. */
    private final String err;

    /**
     * Constructor to create an Error Action.
     * @param err Error description
     */
    public JukeErrorAction(String err) {
        this.err = err;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public Optional<? extends JukeAction> complete() {
        System.out.print(JukeASCIIColours.RED + "Error: " + err + JukeASCIIColours.RESET);
        return Optional.empty();
    }
}
