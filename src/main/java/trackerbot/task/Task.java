package trackerbot.task;

import trackerbot.exception.TrackerBotException;

/**
 * The Task class for TrackerBot.
 * <p>The Task class abstracts each checklist item inside a Reminder-Type app.</p>
 * <p>Task should not be instantiated as a basic task. To instantiate a basic Task
 * with no DateTime fields, use the To-do subtype instead.</p>
 *
 * @author WZWren
 * @version A-CodeQuality
 */
public abstract class Task {
    /** The description of the task instance. **/
    private String description;

    /** The status of the task instance. If true, the task is done. */
    private boolean isDone;

    /**
     * Constructs the Task class.
     * <p>This is designed to be called by inheriting classes, using super.</p>
     *
     * @param desc The description of the task to create.
     */
    protected Task(String desc) {
        description = desc;
        isDone = false;
    }

    /**
     * Constructs a Task using a String array, for use in save parsing.
     * <p>This is designed to be called by inheriting classes, using super.</p>
     *
     * @param args The arguments for constructing a Task, containing isDone status in index
     *             0 and description in index 1.
     */
    protected Task(String[] args) {
        description = args[1];
        isDone = args[0].equals("1");
    }

    /**
     * Returns the save string for the child class. <br>
     * The save string should have the following format: <br>
     * {Task Flag} | {Mark Status} | {Description} | {Fields...}
     * <ul>
     *  <li>Task Flag represents the type of Task to create.</li>
     *  <li>Mark Status is a 0/1 representation of the Checkmark status.</li>
     *  <li>Description is the main body of the Task item to display.</li>
     *  <li>Fields are the additional fields required by the Task, delimited by '|'.</li>
     * </ul>
     *
     * @return The '|' delimited String to store in save file.
     */
    public abstract String toSaveString();

    /**
     * Constructs Tasks using inputs from the savd file.
     * <p>ofSaveString expects the save string to be split before passing into Task.</p>
     *
     * @param type The String representation of the Task in the save file.
     * @param args The Arguments for each task in the save file.
     * @return A Task corresponding to the type and args of the save string.
     * @throws TrackerBotException if the save file is corrupted, or if
     *                             the DateTime field is invalid.
     */
    public static Task ofSaveString(String type, String... args)
            throws TrackerBotException {
        switch (type) {
        case "T":
            if (args.length != 2) {
                throw new TrackerBotException("Todos should have exactly 2 arguments.");
            }
            return new Todo(args);
        case "D":
            if (args.length != 3) {
                throw new TrackerBotException("Deadline should have exactly 3 arguments.");
            }
            return new Deadline(args);
        case "E":
            if (args.length != 4) {
                throw new TrackerBotException("Events should have exactly 4 arguments.");
            }
            return new Event(args);
        default:
            throw new IllegalStateException("Unknown Task type.");
        }
    }

    /**
     * Helps toSaveString generate a save string. <br>
     * Gets a formatted description and mark status of the Task, and
     * passes it to the child classes.
     *
     * @return {Mark Status} | {Description} String, to append to toSaveString
     *         implementation in child classes.
     */
    protected String getSaveInfo() {
        String checkStatus = isDone ? "1" : "0";
        return checkStatus + "|" + description;
    }

    /**
     * Helps determine the checkmark status of the Task.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    private String getCheckbox() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Flags the task to be completed, if able.
     *
     * @throws TrackerBotException if the Task is already done.
     */
    public void markTask() throws TrackerBotException {
        if (isDone) {
            throw new TrackerBotException("The specified task is already completed.");
        }
        isDone = true;
    }

    /**
     * Flags the task to be incomplete, if able.
     *
     * @throws TrackerBotException if the Task is already still in progress.
     */
    public void unmarkTask() throws TrackerBotException {
        if (!isDone) {
            throw new TrackerBotException("This task is already in progress.");
        }
        isDone = false;
    }

    /**
     * Checks if the description of the Task contains the query String.
     *
     * @param searchStr The query to match any point in the description.
     * @return true, if the description contains the searchStr, and false
     *         otherwise.
     */
    public boolean doesContain(String searchStr) {
        return description.contains(searchStr);
    }

    /**
     * Returns the String representation of the Task.
     * <p>A Task is formatted as "[X] description of task", where the X may or
     * may not be present depending on the completion status of the task.</p>
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return getCheckbox() + " " + description;
    }
}
