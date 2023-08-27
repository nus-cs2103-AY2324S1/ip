package juke.commands;

/**
 * Action that is invoked when the user wishes to quit the assistant.
 */
public class JukeExitCommand extends JukeCommand {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT_STRING = "Goodbye!";

    /**
     * Creates an instance of {@code JukeExitCommand}.
     */
    public JukeExitCommand() {}

    /**
     * Carries out an action when the command is executed.
     */
    @Override
    public void execute() {
        System.out.print(JukeExitCommand.EXIT_STRING);
    }
}
