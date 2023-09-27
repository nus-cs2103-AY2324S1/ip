package rat.command;

import static rat.io.RatPrinter.printExit;

import rat.notes.RatNoteManager;
import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that exits the program.
 */
public class ExitCommand extends RatCommand {

    /**
     * Constructor for an ExitCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param ratNoteManager The RatNoteManager object used to store and process the user's notes.
     */
    public ExitCommand(RatTaskManager ratTaskManager, RatNoteManager ratNoteManager) {
        super(ratTaskManager, ratNoteManager);
    }

    @Override
    public String getResponse() {
        this.ratTaskManager.save();
        this.ratNoteManager.save();
        printExit();
        System.exit(0);
        return "";
    }

    @Override
    public void execute() {
        this.ratTaskManager.save();
        this.ratNoteManager.save();
        printExit();
        System.exit(0);
    }
}
