package arona.commands;

/**
 * An interface representing a command that can be undone.
 */
public interface UndoableCommand {
    /**
     * Undoes the previous action performed by this command.
     *
     * @return A string message indicating the result of the undo operation.
     */
    String undo();

    /**
     * Retrieves the task index associated with this undoable command.
     *
     * @return The task index or -1 if not applicable.
     */
    int getTaskIndex();
}

