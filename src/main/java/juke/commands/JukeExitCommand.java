package juke.commands;

/**
 * Action that is invoked when the user wishes to quit the assistant.
 */
public class JukeExitCommand extends JukeCommand {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT = "Goodbye!";

    /**
     * JukeExitCommand Constructor.
     */
    public JukeExitCommand() {}

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void complete() {
        System.out.print(EXIT);
    }
}
