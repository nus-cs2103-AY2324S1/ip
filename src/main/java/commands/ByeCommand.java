package commands;

/**
 * Command to display the goodbye message.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for the ByeCommand which does nothing.
     */
    public ByeCommand() { }

    /**
     * ByeCommand implements isTerminateCommand to
     * indicate to Dot to stop the program.
     * @return true
     */
    @Override
    public boolean isTerminateCommand() {
        return true;
    };


}
