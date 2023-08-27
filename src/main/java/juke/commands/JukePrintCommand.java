package juke.commands;

import juke.core.JukeObject;

/**
 * Action that invokes a print action on the command line.
 */
public class JukePrintCommand extends JukeCommand {
    /** Object to print. */
    private final JukeObject printTarget;

    /**
     * Constructor for JukePrintAction.
     * @param printTarget Object to print
     */
    public JukePrintCommand(JukeObject printTarget) {
        this.printTarget = printTarget;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void execute() {
        System.out.print(printTarget);
    }
}
