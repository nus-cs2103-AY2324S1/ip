package juke.commands;

import juke.exceptions.JukeException;

/**
 * Action that is invoked when the user wishes to quit the assistant.
 */
public class JukeExitCommand extends JukeCommand {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT_STRING = "Goodbye!";

    /**
     * JukeExitAction Constructor.
     */
    public JukeExitCommand() {}

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void execute() throws JukeException {
        System.out.print(JukeExitCommand.EXIT_STRING);
    }
}
