package rat.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates the commands that can be executed by Rat.
 * Each command has a corresponding 'execute' method that is called by RatInput.
 */
public abstract class RatCommand {

    /**
     * The RatTaskManager object used to store and process the user's tasks.
     * This RatTaskManager should be initialised in main.
     */
    protected final RatTaskManager ratTaskManager;

    /**
     * Constructor for a RatCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     */
    protected RatCommand(RatTaskManager ratTaskManager) {
        this.ratTaskManager = ratTaskManager;
    }

    /**
     * Executes the command.
     */
    public abstract void execute();

    public abstract String getResponse();

    /**
     * Validates the time input by the user.
     * @param time The time input by the user.
     * @return True if the time is valid, false otherwise.
     * @throws ParseException If the time is not in the correct format.
     */
    protected static boolean validateTime(String time) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.parse(time.trim());
        return true;
    }

}
