package juke.commands;

import juke.core.JukeObject;

/**
 * Action that invokes a print action on the command line.
 */
public class JukePrintCommand extends JukeCommand {
    /** Object to print. */
    private final JukeObject printTarget;

    /**
     * Creates an instance of {@code JukePrintCommand}.
     *
     * @param printTarget {@code Object} to print
     */
    public JukePrintCommand(JukeObject printTarget) {
        this.printTarget = printTarget;
    }

    /**
     * Carries out an action when the command is executed.
     */
    @Override
    public void execute() {
        System.out.print(printTarget);
    }
}
