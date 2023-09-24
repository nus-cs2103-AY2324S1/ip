package dot.commands;

/**
 * Command to display the goodbye message.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for the ByeCommand which does nothing.
     */
    public ByeCommand() {
    }

    @Override
    public boolean isTerminateCommand() {
        return true;
    }

}
