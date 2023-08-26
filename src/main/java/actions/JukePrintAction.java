package main.java.actions;

/**
 * Action that invokes a print action on the command line.
 */
public class JukePrintAction extends JukeAction {
    /** Object to print. */
    private final Object printTarget;

    /**
     * Constructor for JukePrintAction.
     * @param printTarget Object to print
     */
    public JukePrintAction(Object printTarget) {
        this.printTarget = printTarget;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public void complete() {
        System.out.print(printTarget);
    }
}
