package rat.command;

import static rat.io.RatPrinter.printExit;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that exits the program.
 */
public class ExitCommand extends RatCommand {

    /**
     * Constructor for an ExitCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     */
    public ExitCommand(RatTaskManager ratTaskManager) {
        super(ratTaskManager);
    }

    @Override
    public String getResponse() {
        this.ratTaskManager.save();
        printExit();
        System.exit(0);
        return "";
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        this.ratTaskManager.save();
        printExit();
        System.exit(0);
    }
}
