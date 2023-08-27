package juke.commands;

/**
 * Action that invokes a print action on the command line.
 */
public class JukePrintCommand extends JukeCommand {
    /** Object to print. */
    private final Object printTarget;

    /**
     * Constructor for {@code JukePrintCommand}.
     *
     * @param printTarget {@code Object} to print
     */
    public JukePrintCommand(Object printTarget) {
        this.printTarget = printTarget;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void complete() {
        System.out.print(printTarget);
    }
}
