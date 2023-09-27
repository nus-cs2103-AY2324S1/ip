package rat.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import rat.notes.RatNoteManager;
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
    protected RatTaskManager ratTaskManager;

    /**
     * The RatNoteManager object used to store and process the user's notes.
     * This RatNoteManager should be initialised in main.
     */
    protected RatNoteManager ratNoteManager;

    /**
     * Constructor for a RatCommand object.
     * This constructor is called when dealing with commands that modify the task list.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     */
    protected RatCommand(RatTaskManager ratTaskManager) {
        this.ratTaskManager = ratTaskManager;
        this.ratNoteManager = null;
    }

    /**
     * Constructor for a RatCommand object.
     * This constructor is called when dealing with commands that modify the note list.
     * @param ratNoteManager The RatNoteManager object used to store and process the user's notes.
     */
    protected RatCommand(RatNoteManager ratNoteManager) {
        this.ratNoteManager = ratNoteManager;
        this.ratTaskManager = null;
    }

    /**
     * Constructor for a RatCommand object.
     * This constructor is called when dealing with commands that modify both the task and note lists.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param ratNoteManager The RatNoteManager object used to store and process the user's notes.
     */
    protected RatCommand(RatTaskManager ratTaskManager, RatNoteManager ratNoteManager) {
        this.ratTaskManager = ratTaskManager;
        this.ratNoteManager = ratNoteManager;
    }

    /**
     * Executes the command which modifies the task list.
     * Able to create, delete, and update tasks.
     */
    public abstract void execute();

    /**
     * Calls the respective methods to modify the task list.
     * @return The response to be printed to the user.
     */
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
