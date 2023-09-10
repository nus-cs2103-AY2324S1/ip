package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;

/**
 * An interface which all undoable Commands extend from.
 * Commands which are not undoable do not extend from here.
 * We don't use polymorphism here. We have a nullable Undoable
 * in Dot, which will be non-null if there is a Command to undo.
 * <p>
 * As implementation for respective undoes can require certain
 * data, those fields are to be specified in the subclasses.
 * <p>
 * To define a new undoable Command & things to note:
 * <ul>
 * <li>Extend Undoable and override both methods, defining needed
 *     fields.</li>
 * <li>Remember to populate above fields when executing.</li>
 * <li>Note the assumption that the undo method only applies if the
 *     cmd to be undone is the latest Undoable command. </li>
 * <li>Hence the set of undo attributes and methods become
 *     obsolete after another Undoable command executes.</li>
 * <li>It is recommended that Undoable Commands' undo
 *     method be called in the same level as Command::execute.
 *     Storage of the latest Undoable is to be stored there too.</li>
 * <li>Use DotException etc. to determine whether a Command is
 *     successful, and hence whether to store it at latest undo.</li>
 * <li>Undo can only be performed once. This logic can be defined
 *     in the same file as command execution.</li>
 * <li>Invariant 1: A Command which extends Undoable must have
 *     called execute, before it is able to call undo.
 *     This logic is to be implemented in same file as execution.</li>
 * <li>Invariant 2: Once a command is undone, it cannot be repeated,
 *     even if the undo fails.</li>
 * <li>Invariant 3: An undo cannot be undone.</li>
 * <li>Invariant 4: If you choose to selectively implement Undoable,
 *     make sure default isUndoable() returns false, and those
 *     classes that extend Undoable will return true for this.</li>
 * </ul>
 */
public interface Undoable {
    /**
     * The undo method for the corresponding Command subclass.
     * Command::undo to be called to undo the specific action.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     */
    void undo(Consumer<String> handleDotOutput) throws DotException;

    /**
     * Forces implementing classes to override isUndoable method
     * descriptor which the implementation to return true.
     *
     * @return True.
     */
    boolean isUndoable();
}
